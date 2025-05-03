package com.example.postech_tc4_pedido_receiver.entities;

public class PagamentoEntity {
    private String numeroCartao;

    public PagamentoEntity(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.isBlank()) {
            throw new IllegalArgumentException("O número do cartão é obrigatório");
        }
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
