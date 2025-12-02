package org.sistema.messaging;

import org.sistema.config.RabbitConfigEstoque;
import org.sistema.dto.ItemDTO;
import org.sistema.dto.PedidoDTO;
import org.sistema.model.Produto;
import org.sistema.repository.ProdutoRepository; 
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; 

@Component
public class EstoqueConsumer {

    @Autowired
    private EstoqueResponsePublisher publisher;

    @Autowired
    private ProdutoRepository produtoRepository; 

    @Transactional 
    @RabbitListener(queues = RabbitConfigEstoque.FILA_ESTOQUE)
    public void receberPedido(PedidoDTO pedidoDTO) {
        System.out.println("[EstoqueConsumer] Mensagem recebida: " + pedidoDTO);

        Long pedidoId = pedidoDTO.getId();
        String statusFinal = "ESTOQUE_CONFIRMADO";

        try {
            for (ItemDTO item : pedidoDTO.getItens()) {
                Produto produto = produtoRepository.findById(item.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProdutoId()));

                //verifica se há estoque suficiente
                if (produto.getQuantidadeEmEstoque() < item.getQuantidade()) {
                    statusFinal = "FALHA_ESTOQUE";
                    System.out.println("-> [Estoque] FALHA: Estoque insuficiente para o Produto " + produto.getNome());
                    
                    break; //se falhar em um item, para a verificação do pedido inteiro 
                }
            }

            // verificação passou para todos os itens: statusFinal = "ESTOQUE_CONFIRMADO"
            if ("ESTOQUE_CONFIRMADO".equals(statusFinal)) {
                for (ItemDTO item : pedidoDTO.getItens()) {
                    Produto produto = produtoRepository.findById(item.getProdutoId()).get(); 
                    
                    Integer novaQuantidade = produto.getQuantidadeEmEstoque() - item.getQuantidade();
                    produto.setQuantidadeEmEstoque(novaQuantidade);
                    
                    produtoRepository.save(produto); 
                    System.out.println("-> [Estoque] Baixa efetuada: " + item.getQuantidade() + " unidades de " + produto.getNome());
                }
            }
            
        } catch (RuntimeException e) {
            // se produto não é encontrado, o status é falha
            statusFinal = "FALHA_ESTOQUE";
            System.out.println("-> [Estoque] Erro no processamento: " + e.getMessage());
        }

        publisher.enviarStatus(pedidoId, statusFinal);
        System.out.println("[EstoqueResponsePublisher] Enviado status final do Pedido " + pedidoId + ": " + statusFinal);
    }
}