package org.sistema.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigEstoque {

    public static final String FILA_ESTOQUE = "fila.estoque";

    @Bean
    public Queue queueEstoque() {
        return new Queue(FILA_ESTOQUE, true);
    }
}
