package org.sistema.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigInterface {

    public static final String FILA_INTERFACE = "fila.interface";

    @Bean
    public Queue queueInterface() {
        return new Queue(FILA_INTERFACE, true);
    }
}
