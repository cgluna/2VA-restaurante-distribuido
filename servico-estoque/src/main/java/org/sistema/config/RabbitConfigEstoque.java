package org.sistema.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigEstoque {

    public static final String FILA_ESTOQUE = "fila.estoque";
    public static final String EXCHANGE_RESPOSTA = "exchange.pedidos.resposta";

    @Bean
    public Queue queueEstoque() {
        return new Queue(FILA_ESTOQUE, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        
        converter.addAllowedListPatterns(
            "java.util.ArrayList",      
            "org.sistema.dto.*"        
        );

        return converter;
    }

    @Bean
    public TopicExchange respostaExchange() {
        return new TopicExchange(EXCHANGE_RESPOSTA); 
    }
}