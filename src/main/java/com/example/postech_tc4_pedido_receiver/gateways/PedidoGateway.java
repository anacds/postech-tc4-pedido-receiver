package com.example.postech_tc4_pedido_receiver.gateways;

import com.example.postech_tc4_pedido_receiver.entities.PedidoEntity;
import com.example.postech_tc4_pedido_receiver.interfaces.IPedidoGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoGateway implements IPedidoGateway {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.novo-pedido}")
    private String topicoNovoPedido;

    public PedidoGateway(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void postarPedidoFila(PedidoEntity pedido) {
        try {
            String mensagem = objectMapper.writeValueAsString(pedido);
            kafkaTemplate.send(topicoNovoPedido, mensagem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ocorreu um erro ao serializar o pedido", e);
        }
    }
}

