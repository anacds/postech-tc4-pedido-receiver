FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app-pedido-receiver
COPY . /app-pedido-receiver
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app-pedido-receiver
COPY --from=build /app-pedido-receiver/target/postech-tc4-pedido-receiver-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

CMD ["java", "-jar", "app.jar"]