# Stage 1: Build
FROM ubuntu:24.04 AS build
LABEL authors="anthony"

# Ajusta mirrors para o Brasil e instala OpenJDK 17 + Maven
RUN sed -i 's|http://archive.ubuntu.com/ubuntu/|http://br.archive.ubuntu.com/ubuntu/|g' /etc/apt/sources.list \
    && sed -i 's|http://security.ubuntu.com/ubuntu/|http://br.archive.ubuntu.com/ubuntu/|g' /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -y openjdk-17-jdk maven \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copia todo o projeto
COPY . .

# Build do Maven (ignora testes)
RUN mvn clean install -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app
EXPOSE 8080

# Copia o JAR buildado do stage 1
COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

# Comando padrão para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
