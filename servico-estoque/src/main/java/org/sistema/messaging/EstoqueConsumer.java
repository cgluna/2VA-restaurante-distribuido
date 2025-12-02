package org.sistema.messaging;

import org.sistema.config.RabbitConfigEstoque;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.sistema.dto.PedidoDTO;

@Component
public class EstoqueConsumer {

    @Autowired
    private EstoqueResponsePublisher publisher;

    @RabbitListener(queues = RabbitConfigEstoque.FILA_ESTOQUE)
    public void receberPedido(PedidoDTO pedidoDTO) {
        System.out.println("[EstoqueConsumer] Mensagem recebida: " + pedidoDTO);

        // ** SIMULAÇÃO de Lógica de Estoque **
        Long pedidoId = pedidoDTO.getId();
        String statusFinal;
        
        // Simulação de Sucesso/Falha
        if (pedidoId % 2 != 0) { // o primeiro Pedido (ID=1) da FALHA, o segundo (ID=2) da sucesso, e assim por diante
            statusFinal = "ESTOQUE_CONFIRMADO";
            System.out.println("-> [Estoque] Estoque confirmado para o Pedido " + pedidoId);
        } else {
            statusFinal = "FALHA_ESTOQUE";
            System.out.println("-> [Estoque] Falha na baixa de estoque para o Pedido " + pedidoId);
        }

        publisher.enviarStatus(pedidoId, statusFinal);
    }


}