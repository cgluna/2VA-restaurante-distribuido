package org.sistema.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigFuncionarios {

    public static final String FILA_FUNCIONARIOS = "fila.funcionarios";

    @Bean
    public Queue queueFuncionarios() {
        return new Queue(FILA_FUNCIONARIOS, true);
    }
}
