package com.example.postech_tc4_pedido_receiver.entities;

public class ProdutoEntity {
    private String id;
    private String sku;
    private int quantidade;

    private String nome;

    public ProdutoEntity(String sku, int quantidade) {
        super();
        this.sku = sku;
        this.quantidade = quantidade;
    }

    public String getSKU() {
        return sku;
    }
    public int getQuantidade() {
        return quantidade;
    }
}
