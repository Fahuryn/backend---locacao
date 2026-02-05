# Backend Locação – API de Clientes

API REST desenvolvida em Java com Spring Boot para gerenciamento de clientes, permitindo operações de criação, consulta, atualização e exclusão (CRUD).

Projeto desenvolvido como teste técnico e material de estudo, seguindo boas práticas de organização, separação de camadas e arquitetura backend.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Bean Validation
- Flyway
- PostgreSQL
- Swagger OpenAPI
- Maven
- Postman

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java 17 ou superior
- Maven
- PostgreSQL
- IntelliJ IDEA (opcional)

---

### Executando o projeto

#### Opção 1 – Via Maven (recomendado)

1. Abra o terminal na pasta do projeto (onde está o arquivo `pom.xml`)
2. Execute o comando:

```bash
mvn spring-boot:run
```

3. Aguarde a aplicação iniciar

A aplicação estará disponível em:

http://localhost:8080

4. A documentação da API (Swagger) pode ser acessada em:

http://localhost:8080/swagger-ui.html

---

## 🔗 Endpoints Principais

- GET /clientes — Lista todos os clientes
- GET /clientes/{id} — Busca cliente por ID
- POST /clientes — Cadastra um novo cliente
- PUT /clientes/{id} — Atualiza um cliente
- DELETE /clientes/{id} — Remove um cliente
