package com.example.postech_tc4_pedido_receiver.controller;

import com.example.postech_tc4_pedido_receiver.dto.PedidoDTO;
import com.example.postech_tc4_pedido_receiver.entities.ClienteEntity;
import com.example.postech_tc4_pedido_receiver.entities.PagamentoEntity;
import com.example.postech_tc4_pedido_receiver.entities.ProdutoEntity;
import com.example.postech_tc4_pedido_receiver.entities.StatusPedidoEnum;
import com.example.postech_tc4_pedido_receiver.usecases.PostarPedidoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
@Import(PedidoControllerTest.TestConfig.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostarPedidoUseCase postarPedidoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public PostarPedidoUseCase postarPedidoUseCase() {
            return mock(PostarPedidoUseCase.class);
        }
    }

    @Test
    public void deveAceitarPedidoValidoERetornarStatus202() throws Exception {
        ClienteEntity cliente = new ClienteEntity("Maria Silva", "11111111111");
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 2, "Nome do produto", "CODIGODEBARRAS", "Descrição do produto", "Fabricante do produto", 9.99, "ARTIGOS ESPORTIVOS");
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");
        StatusPedidoEnum status = StatusPedidoEnum.ABERTO;
        PedidoDTO pedidoDTO = new PedidoDTO(cliente, List.of(produto), pagamento, status);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isAccepted());

        verify(postarPedidoUseCase).postarPedido(any(PedidoDTO.class));
    }

    @Test
    public void deveRetornar400QuandoPayloadInvalido() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO(null, null, null, null);

        doThrow(new IllegalArgumentException("Payload inválido"))
                .when(postarPedidoUseCase).postarPedido(any(PedidoDTO.class));

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isBadRequest());
    }
}