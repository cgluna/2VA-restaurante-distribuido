package org.sistema.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigPedidos {

    public static final String FILA_ESTOQUE = "fila.estoque";
    public static final String EXCHANGE_RESPOSTA = "exchange.pedidos.resposta";
    public static final String FILA_RESPOSTA = "fila.pedidos.resposta"; 
    private static final String ROUTING_KEY_PEDIDOS = "pedidos.status.atualizacao";

    @Bean
    public Queue queueEstoque() {
        return new Queue(FILA_ESTOQUE, true);
    }

    @Bean
    public TopicExchange respostaExchange() {
        return new TopicExchange(EXCHANGE_RESPOSTA);
    }

    @Bean
    public Queue respostaQueue() {
        return new Queue(FILA_RESPOSTA);
    }

    @Bean
    public Binding respostaBinding(Queue respostaQueue, TopicExchange respostaExchange) {
        return BindingBuilder.bind(respostaQueue).to(respostaExchange).with(ROUTING_KEY_PEDIDOS);
    }
}
