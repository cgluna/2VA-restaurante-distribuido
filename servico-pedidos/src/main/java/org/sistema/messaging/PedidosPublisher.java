package org.sistema.messaging;

import org.sistema.config.RabbitConfigPedidos;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidosPublisher {

    @Autowired
    private AmqpTemplate amqpTemplate;

    // Envia um JSON (ou texto simples) para a fila de estoque
    public void enviarPedido(String jsonPedido) {
        amqpTemplate.convertAndSend(RabbitConfigPedidos.FILA_ESTOQUE, jsonPedido);
        System.out.println("[PedidosPublisher] Enviado para fila.estoque: " + jsonPedido);
    }
}
