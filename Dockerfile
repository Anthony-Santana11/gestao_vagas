FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

# Forçar IPv4 e instalar maven
RUN apt-get update -o Acquire::ForceIPv4=true && \
    apt-get install -y maven -o Acquire::ForceIPv4=true
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]