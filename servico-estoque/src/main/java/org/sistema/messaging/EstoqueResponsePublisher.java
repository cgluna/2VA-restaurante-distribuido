package org.sistema.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueResponsePublisher {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String EXCHANGE_RESPOSTA = "exchange.pedidos.resposta"; 

    private static final String ROUTING_KEY_PEDIDOS = "pedidos.status.atualizacao"; 

    public void enviarStatus(Long pedidoId, String novoStatus) {
        String mensagem = "{\"id\": " + pedidoId + ", \"status\": \"" + novoStatus + "\"}";

        rabbitTemplate.convertAndSend(EXCHANGE_RESPOSTA, ROUTING_KEY_PEDIDOS, mensagem);
        System.out.println("[EstoqueResponsePublisher] Enviado status do Pedido " + pedidoId + ": " + novoStatus);
    }
}