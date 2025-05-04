package com.example.postech_tc4_pedido_receiver.gateways;

import com.example.postech_tc4_pedido_receiver.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoGatewayTest {
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    public void setUp() throws Exception {
        kafkaTemplate = mock(KafkaTemplate.class);
        objectMapper = mock(ObjectMapper.class);
        pedidoGateway = new PedidoGateway(kafkaTemplate, objectMapper);

        Field field = PedidoGateway.class.getDeclaredField("topicoNovoPedido");
        field.setAccessible(true);
        field.set(pedidoGateway, "novo-pedido-queue");
    }

    @Test
    public void deveEnviarPedidoParaOTopicoKafka() throws Exception {
        // Arrange
        ClienteEntity cliente = new ClienteEntity("Maria Silva");
        ProdutoEntity produto = new ProdutoEntity("SKU-001", 1);
        PagamentoEntity pagamento = new PagamentoEntity("4111111111111111");
        StatusPedidoEnum status = StatusPedidoEnum.ABERTO;
        PedidoEntity pedido = new PedidoEntity(cliente, List.of(produto), pagamento, status);

        when(objectMapper.writeValueAsString(eq(pedido))).thenReturn("{\"cliente\":\"Maria Silva\"}");

        // Act
        pedidoGateway.postarPedidoFila(pedido);

        // Assert
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate, times(1)).send(eq("novo-pedido-queue"), captor.capture());

        String json = captor.getValue();
        assertNotNull(json);
        assertTrue(json.contains("Maria Silva"));
    }
}
