package com.example.postech_tc4_pedido_receiver.dto;

import com.example.postech_tc4_pedido_receiver.entities.ClienteEntity;
import com.example.postech_tc4_pedido_receiver.entities.PagamentoEntity;
import com.example.postech_tc4_pedido_receiver.entities.ProdutoEntity;

import java.util.List;

public record PedidoDTO(
        ClienteEntity cliente,
        List<ProdutoEntity> produtos,
        PagamentoEntity dadosPagamento
        ) {
}
