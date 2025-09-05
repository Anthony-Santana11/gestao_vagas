FROM ubuntu:24.04 AS build
LABEL authors="anthony"

ENV DEBIAN_FRONTEND=noninteractive

# Ajusta mirrors + habilita universe
RUN sed -i 's|http://archive.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && sed -i 's|http://security.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -y software-properties-common \
    && add-apt-repository universe \
    && apt-get update \
    && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Runtime
FROM ubuntu:24.04
ENV DEBIAN_FRONTEND=noninteractive
RUN sed -i 's|http://archive.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && sed -i 's|http://security.ubuntu.com/ubuntu/|http://mirror.ufscar.br/ubuntu/|g' /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -y openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
