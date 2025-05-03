package com.example.postech_tc4_pedido_receiver.controller;

import com.example.postech_tc4_pedido_receiver.dto.PedidoDTO;
import com.example.postech_tc4_pedido_receiver.usecases.PostarPedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PostarPedidoUseCase postarPedidoUseCase;

    @PostMapping
    public ResponseEntity<String> postarPedido(@RequestBody PedidoDTO pedidoDTO) {
        postarPedidoUseCase.postarPedido(pedidoDTO);
        return ResponseEntity.accepted().body("O pedido foi recebido com sucesso.");
    }
}
