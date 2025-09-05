# Stage de build
FROM ubuntu:latest AS build
LABEL authors="anthony"

# Ajusta mirror para UFSCAR + instala JDK 17 e Maven
RUN sed -i 's|http://archive.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && sed -i 's|http://security.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -y openjdk-17-jdk maven \
    && rm -rf /var/lib/apt/lists/*

# Copia código e builda com Maven
COPY . .
RUN mvn clean install -DskipTests

# Stage de runtime
FROM openjdk:17-jdk-slim
EXPOSE 8080

# Copia o jar gerado
COPY --from=build /target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
