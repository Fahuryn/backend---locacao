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
* Docker & Docker Compose
* JUnit + Mockito
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

### 🔐 Segurança e Autenticação

A API utiliza **JWT (JSON Web Token)**. Os endpoints protegidos exigem o header `Authorization: Bearer <TOKEN>`.

### Credenciais de Acesso (Padrão de Teste):
As credenciais abaixo são inseridas automaticamente via Flyway (V7):
* **E-mail:** `admin@email.com`
* **Senha:** `123`

### Endpoint de Login
`POST /auth/login`

**Exemplo de Body:**
```json
{
  "email": "admin@email.com",
  "senha": "123"
}
```
---

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

## ▶️ Como executar o projeto (Via Docker)

O projeto está configurado para subir todo o ambiente (Banco de Dados + Aplicação) via Docker Compose.

1. **Gerar o arquivo .jar:**
   ```bash
   mvn clean package -DskipTests
   ```
2. **Subir os containers:**
   ```bash
   mvn clean package -DskipTests
   ```
   
---

## 🧪 Testes e Coleção Postman

Para facilitar a avaliação, incluí uma coleção do Postman pronta para uso.

1. **Arquivo:** Localizado em `/postman/backend_locacao.postman_collection.json`.
2. **Importação:** No Postman, clique em **Import** e selecione o arquivo.
3. **Fluxo sugerido:**
    * Execute o request **Login** para obter o token.
    * Copie o token e configure o **Bearer Token** na aba Authorization dos demais requests.
    * Crie um **Cliente** e um **Tipo de Locação** antes de realizar a **Reserva**.

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
