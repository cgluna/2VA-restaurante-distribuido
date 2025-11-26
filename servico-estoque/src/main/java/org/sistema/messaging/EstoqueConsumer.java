package org.sistema.messaging;

import org.sistema.config.RabbitConfigEstoque;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitConfigEstoque.FILA_ESTOQUE)
    public void receberMensagem(String mensagem) {
        System.out.println("[EstoqueConsumer] Mensagem recebida: " + mensagem);
    }
}
