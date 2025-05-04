package com.example.postech_tc4_pedido_receiver.entities;

import java.util.List;
import java.util.UUID;

public class PedidoEntity {
    private String id;
    private ClienteEntity cliente;
    private List<ProdutoEntity> produtos;
    private PagamentoEntity dadosPagamento;
    private StatusPedidoEnum status;

    public PedidoEntity(ClienteEntity cliente, List<ProdutoEntity> produtos, PagamentoEntity dadosPagamento, StatusPedidoEnum status) {

        if (cliente == null) {
            throw new IllegalArgumentException("É obrigatório informar o cliente");
        }
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser vazia");
        }
        if (dadosPagamento == null) {
            throw new IllegalArgumentException("Os dados de pagamento são obrigatórios");
        }


        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.produtos = produtos;
        this.dadosPagamento = dadosPagamento;
        this.status = StatusPedidoEnum.ABERTO;
    }

    public String getId() {
        return id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public List<ProdutoEntity> getProdutos() { return produtos; }

    public PagamentoEntity getDadosPagamento() { return dadosPagamento; }

    public StatusPedidoEnum getStatus() { return status; }

}
