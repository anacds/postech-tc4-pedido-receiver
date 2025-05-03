package com.example.postech_tc4_pedido_receiver.usecases;

import com.example.postech_tc4_pedido_receiver.dto.PedidoDTO;
import com.example.postech_tc4_pedido_receiver.entities.PedidoEntity;
import com.example.postech_tc4_pedido_receiver.gateways.PedidoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostarPedidoUseCase {

    //@Autowired
    //public PedidoGateway pedidoGateway;

    private final PedidoGateway pedidoGateway;

    public PostarPedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


    public void postarPedido(PedidoDTO pedidoDTO) {

        if (pedidoDTO == null) {
            throw new IllegalArgumentException("Os dados do pedido não podem ser nulos.");
        }

        PedidoEntity pedido = new PedidoEntity(
            pedidoDTO.cliente(),
            pedidoDTO.produtos(),
            pedidoDTO.dadosPagamento()
        );

        pedidoGateway.postarPedidoFila(pedido);
    }
}
