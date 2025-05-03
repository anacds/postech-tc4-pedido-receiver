package com.example.postech_tc4_pedido_receiver.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoEntityTest {
    @Test
    public void deveCriarPedidoValido() {
        ClienteEntity cliente = new ClienteEntity("Maria Silva");
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 1);
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");

        PedidoEntity pedido = new PedidoEntity(cliente, List.of(produto), pagamento);

        assertNotNull(pedido.getId());
        assertEquals("Maria Silva", pedido.getCliente().getNome());
        assertEquals(1, pedido.getProdutos().size());
        assertEquals("4111111111111111", pedido.getDadosPagamento().getNumeroCartao());
    }

    @Test
    public void deveLancarExcecaoSeClienteForNulo() {
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 1);
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new PedidoEntity(null, List.of(produto), pagamento);
        });

        assertEquals("É obrigatório informar o cliente", ex.getMessage());
    }

    @Test
    public void deveLancarExcecaoSeProdutosForemNulosOuVazios() {
        ClienteEntity cliente = new ClienteEntity("Maria Silva");
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
            new PedidoEntity(cliente, null, pagamento);
        });
        assertEquals("A lista de produtos não pode ser vazia", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> {
            new PedidoEntity(cliente, List.of(), pagamento);
        });
        assertEquals("A lista de produtos não pode ser vazia", ex2.getMessage());
    }

    @Test
    public void deveLancarExcecaoSePagamentoForNulo() {
        ClienteEntity cliente = new ClienteEntity("Maria Silva");
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 1);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new PedidoEntity(cliente, List.of(produto), null);
        });

        assertEquals("Os dados de pagamento são obrigatórios", ex.getMessage());
    }
}
