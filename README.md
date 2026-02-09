# Backend de Sistema de Locação

API REST desenvolvida como teste técnico para um sistema de locação, contendo regras de negócio, autenticação JWT e controle de reservas com validação de conflitos de datas.

---

## 🛠️ Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security (JWT)
* Hibernate
* PostgreSQL
* Flyway (migrations)
* Maven
* Postman (testes)

---

## 📌 Funcionalidades

### Clientes

* Criar cliente
* Listar clientes
* Buscar cliente por ID
* Atualizar cliente
* Excluir cliente (**bloqueado se houver reservas vinculadas**)

### Tipos de Locação

* Criar tipo de locação
* Listar tipos de locação
* Buscar por ID
* Atualizar tipo de locação
* Desativar tipo de locação (**bloqueado se houver reservas vinculadas**)

### Reservas

* Criar reserva
* Listar reservas
* Buscar reserva por ID
* Atualizar reserva
* Excluir reserva
* **Validação de conflito de datas (não permite reservas sobrepostas)**

### Disponibilidade

* Endpoint para verificar disponibilidade de um tipo de locação em um período de datas

---

## 🔐 Segurança

* Autenticação baseada em JWT
* Endpoints protegidos exigem token válido
* Endpoint público para login
* Swagger liberado apenas para documentação

---

## 🔑 Login (JWT)

### Endpoint

```
POST /auth/login
```

### Exemplo de body

```json
{
  "email": "admin@teste.com",
  "senha": "123456"
}
```

### Resposta

```json
{
  "token": "jwt-token-aqui"
}
```

Utilize o token retornado no header das requisições protegidas:

```
Authorization: Bearer SEU_TOKEN
```

---

## 📄 Documentação da API
A documentação da API está disponível via Swagger:

http://localhost:8080/swagger-ui/index.html

---

## 📦 Regras de negócio implementadas

* ❌ Não permite criar reservas para datas já reservadas (mesmo tipo de locação)
* ❌ Não permite excluir clientes com reservas vinculadas
* ❌ Não permite excluir tipos de locação com reservas vinculadas
* ✔ Validação de campos obrigatórios
* ✔ Datas de reserva validadas (data final não pode ser anterior à inicial)

---

## ▶️ Como executar o projeto

### Pré-requisitos

* Java 17+
* PostgreSQL
* Maven

### Passos

1. Clone o repositório
2. Configure o banco no `application.yml`
3. Crie o banco de dados no PostgreSQL
4. Execute o projeto:

```
mvn spring-boot:run
```

As migrations serão executadas automaticamente via Flyway.

---

## 🧪 Testes

Os endpoints foram testados utilizando o Postman.

Fluxo sugerido:

1. Criar usuário no banco (para login)
2. Realizar login e obter token
3. Criar cliente
4. Criar tipo de locação
5. Criar reserva
6. Testar conflito de datas
7. Testar bloqueio de exclusões

---

## 🧪 Testes Unitários
O projeto conta com uma suíte de testes unitários utilizando **JUnit 5** e **Mockito**, focando em:
* **Autenticação**: Validação de login e geração de tokens JWT.
* **Regras de Negócio**: Garantia de integridade nas reservas, impedindo conflitos de datas.

---

## 📄 Observações finais

Projeto desenvolvido com foco em boas práticas:

* Separação de camadas (Controller, Service, Repository)
* Regras de negócio centralizadas no Service
* Uso de DTOs e Mappers
* Código limpo e organizado

---

👤 **Autor**: José Neto
