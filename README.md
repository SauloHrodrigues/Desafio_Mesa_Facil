# API - Mesa Facil

A API mesa fácil foi solicitada para testar meus conhecimentos nas tecnologias 
empregada, bem como minhas capacidades de desenvolver o produto em acordo com as
metodologias ageis. A API desenvolvida tem o objetivo gerenciar reservas de mesas em
restaurantes.
---
## Projeto Solicitado

### Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades:

* Cadastrar novos restaurantes
* Cadastrar clientes
* Criar reservas de mesas para um restaurante em determinado dia e horário
* Cancelar reservas
* Listar reservas existentes


Para fins de exercício, a solução deve ser construída em Java no backend. Frameworks e
bibliotecas são de livre escolha (desde que não infrinjam direitos de uso).

É importante que os restaurantes, clientes e reservas sejam persistidos e não se percam
com o restart da aplicação.

O foco dessa avaliação é a organização, clareza e boas práticas na implementação da
API REST.

A comunicação deve ser feita através de mensagens JSON, e o formato das mesmas fica
a seu critério.

### Como proceder

Por favor, implemente sua solução no seu repositório GitHub.

Ao final, notifique da conclusão para que possamos analisar o código implementado.

Lembre-se de deixar todas as orientações necessárias para executar o seu código.

### O que será analisado

* Simplicidade no design da solução (evitar overengineering)
* Organização e arquitetura do código
* Boas práticas (legibilidade, manutenibilidade, padrões)
* Possíveis bugs
* Tratamento de erros e exceções
* Explicação breve das escolhas técnicas
* Uso de testes automatizados
* Limpeza do código
* Documentação da API e do projeto
* Logs e mensagens claras
* Organização dos commits

---
## Tecnologias ultilizadas


![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-0066CC?style=for-the-badge&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![REST Assured](https://img.shields.io/badge/REST_Assured-000000?style=for-the-badge&logo=swagger&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-00C7B7?style=for-the-badge&logo=mockito&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---
## Pré-requisitos

- Docker instalado ([link](https://docs.docker.com/get-docker/))
- Docker Compose instalado (normalmente já vem com Docker Desktop)

---

## Como rodar o projeto

1. Clonar o projeto;
2. Baixar as dependências no pom.xml;
3. Criar um arquivo **.env** na raiz do projeto, com as seguintes váriaveis, preenchidas:

```bash
DB_URL=
POSTGRES_DB=
POSTGRES_PASSWORD=
POSTGRES_USER=
```
* Rodar o container;
* Dar play;

##### Projeto no GitHub:  https://github.com/SauloHrodrigues/Desafio_Mesa_Facil.git
## Autor:

### Nome: Saulo Henrique Rodrigues

##### LinkedIn: https://www.linkedin.com/in/saulohenriquerodrigues/

##### Swagger: http://localhost:8080/swagger-ui.html