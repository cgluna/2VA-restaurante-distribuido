package org.sistema.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigPedidos {

    public static final String FILA_PEDIDOS = "fila.pedidos";

    @Bean
    public Queue queuePedidos() {
        return new Queue(FILA_PEDIDOS, true);
    }
}
