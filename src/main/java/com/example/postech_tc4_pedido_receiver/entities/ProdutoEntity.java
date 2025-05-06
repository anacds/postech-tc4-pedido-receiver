package com.example.postech_tc4_pedido_receiver.entities;

public class ProdutoEntity {
    private String sku;
    private int quantidade;
    private String nome;
    private String codigoDeBarras;
    private String descricao;
    private String fabricante;
    private Double preco;
    private String categoria;

    public ProdutoEntity(String sku,
                         int quantidade,
                         String nome,
                         String codigoDeBarras,
                         String descricao,
                         String fabricante,
                         Double preco,
                         String categoria) {
        super();
        this.sku = sku;
        this.nome = nome;
        this.quantidade = quantidade;
        this.codigoDeBarras = codigoDeBarras;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getSKU() {
        return sku;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public String getNome() {return nome;};
    public String getCodigoDeBarras() {return codigoDeBarras;};
    public String getDescricao() {return descricao;};
    public String getFabricante() {return fabricante;};
    public Double getPreco() {return preco;};
    public String categoria() {return categoria;}
}
