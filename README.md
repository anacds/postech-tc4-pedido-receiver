# Pedido Receiver – FIAP Tech Challenge 4

Microsserviço responsável por receber pedidos via API HTTP, validá-los e publicá-los em um tópico Kafka para processamento posterior.

## Tecnologias 
- Java 21
- Spring Boot 3.4.5
- Spring Kafka
- Maven
- Docker
- Kafka e Zookeper (via Docker)
- JUnit + Mockito + Jacoco

## Funcionalidades

- Endpoint HTTP `POST /api/pedido` para envio de pedidos.
- Validação dos dados obrigatórios no payload.
- Geração de ID de pedido no momento do recebimento.
- Publicação do pedido validado no tópico Kafka `novo-pedido-queue`.
- Testes unitários.


---

## Como executar

```bash
docker network create app-net

docker run -d --name zookeeper \
  --network app-net \
  -p 2181:2181 \
  -e ALLOW_ANONYMOUS_LOGIN=yes \
  bitnami/zookeeper:latest

docker run -d --name kafka \
  --network app-net \
  -p 9092:9092 \
  -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e ALLOW_PLAINTEXT_LISTENER=yes \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 \
  bitnami/kafka:3.4.0

docker run -d --name app-pedido-receiver --network app-net -p 8084:8084 anacsilva/app-pedido-receiver:1.0
```

## Como testar

 Endpoint:
`POST http://localhost:8084/api/pedido`


Exemplo de payload: 
```json
{
  "cliente": {
    "nome": "João Silva"
  },
  "produtos": [
    {
      "sku": "SKU-001",
      "quantidade": 2
    },
    {
      "sku": "SKU-002",
      "quantidade": 1
    }
  ],
  "dadosPagamento": {
    "numeroCartao": "1111111111111111"
  }
}
```


