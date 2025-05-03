package com.example.postech_tc4_pedido_receiver.interfaces;

import com.example.postech_tc4_pedido_receiver.entities.PedidoEntity;

public interface IPedidoGateway {
        void postarPedidoFila(PedidoEntity pedido);
}
