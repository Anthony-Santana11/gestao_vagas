# Gestão de Vagas

Projeto desenvolvido em Java com Spring Boot para gerenciamento de candidatos e empresas.

## Funcionalidades

- Cadastro de candidatos
- Autenticação de empresas
- Visualização de perfil de candidato
- Integração com banco de dados via JPA

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- JPA/Hibernate
- Banco de dados relacional (ex: PostgreSQL, MySQL)

## Como executar

1. Clone o repositório:
 git clone https://github.com/Anthony-Santana11/gestao_vagas.git
2. Configure o banco de dados em `src/main/resources/application.properties`.
3. Execute o projeto:
   mvn spring-boot:run


   ## Endpoints principais

- `POST /candidate/` — Cadastro de candidato
- `GET /candidate/` — Visualização de perfil do candidato

## Estrutura do projeto

- `modules/candidate` — Lógica de candidatos
- `modules/company` — Lógica de empresas

## Contribuição

Pull requests são bem-vindos! Para grandes mudanças, abra uma issue primeiro.

## Licença

Este projeto está sob a licença MIT.

## Aviso

🚧 Este projeto ainda está em desenvolvimento. Funcionalidades, endpoints e estrutura podem sofrer alterações.
