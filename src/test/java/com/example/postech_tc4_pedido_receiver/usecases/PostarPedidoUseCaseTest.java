package com.example.postech_tc4_pedido_receiver.usecases;

import com.example.postech_tc4_pedido_receiver.dto.PedidoDTO;
import com.example.postech_tc4_pedido_receiver.entities.*;
import com.example.postech_tc4_pedido_receiver.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostarPedidoUseCaseTest {
    private PedidoGateway pedidoGateway;
    private PostarPedidoUseCase useCase;

    @BeforeEach
    public void setUp() {
        pedidoGateway = mock(PedidoGateway.class);
        useCase = new PostarPedidoUseCase(pedidoGateway);
    }

    @Test
    public void devePostarPedidoComSucesso() {

        ClienteEntity cliente = new ClienteEntity("Maria Silva");
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 2, "Nome do produto", "CODIGODEBARRAS", "Descrição do produto", "Fabricante do produto", 9.99, "ARTIGOS ESPORTIVOS");
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");
        StatusPedidoEnum status = StatusPedidoEnum.ABERTO;
        PedidoDTO dto = new PedidoDTO(cliente, List.of(produto), pagamento, status);

        useCase.postarPedido(dto);

        ArgumentCaptor<PedidoEntity> captor = ArgumentCaptor.forClass(com.example.postech_tc4_pedido_receiver.entities.PedidoEntity.class);
        verify(pedidoGateway, times(1)).postarPedidoFila(captor.capture());
        assertNotNull(captor.getValue().getId());
        assertEquals("Maria Silva", captor.getValue().getCliente().getNome());
    }

    @Test
    public void deveLancarExcecaoQuandoPedidoDTOForNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.postarPedido(null));
        assertEquals("Os dados do pedido não podem ser nulos.", exception.getMessage());
        verify(pedidoGateway, never()).postarPedidoFila(any());
    }
}
