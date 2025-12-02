package org.sistema.messaging;

import org.sistema.model.Pedido;
import org.sistema.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson; 

@Component
public class EstoqueResponseConsumer {

    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "fila.pedidos.resposta")
    public void receberStatus(String mensagemJson) {

        Gson gson = new Gson();
        StatusUpdate statusUpdate = gson.fromJson(mensagemJson, StatusUpdate.class); 

        //Buscar o pedido no banco
        Pedido pedido = pedidoRepository.findById(statusUpdate.getId())
            .orElse(null);

        if (pedido != null) {
            // Atualizar o status
            pedido.setStatus(statusUpdate.getStatus());
            pedidoRepository.save(pedido);

            System.out.println("[EstoqueResponseConsumer] Pedido " + pedido.getId() 
                               + " atualizado para o status: " + statusUpdate.getStatus());
        }
    }

    private static class StatusUpdate {
        private Long id;
        private String status;

        public Long getId() { return id; }
        public String getStatus() { return status; }
    }
}