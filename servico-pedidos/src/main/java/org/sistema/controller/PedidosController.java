package org.sistema.controller;

import org.sistema.messaging.PedidosPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosPublisher publisher;

    @PostMapping("/enviar")
    public String enviarPedido(@RequestBody String pedidoJson) {
        publisher.enviarPedido(pedidoJson);
        return "Pedido enviado com sucesso!";
    }
}
