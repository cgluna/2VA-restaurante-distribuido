package org.sistema.messaging;

import org.sistema.config.RabbitConfigPedidos;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.sistema.dto.PedidoDTO;

@Component
public class PedidosPublisher {

    @Autowired
    private AmqpTemplate amqpTemplate;

    // Envia um JSON para a fila de estoque
    public void enviarPedido(PedidoDTO pedidoDTO) {
        amqpTemplate.convertAndSend(RabbitConfigPedidos.FILA_ESTOQUE, pedidoDTO);
        System.out.println("[PedidosPublisher] Enviado para fila.estoque: " + pedidoDTO);
    }
}
