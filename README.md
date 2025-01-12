# ForumHub - API de Simulação de Fórum 🗣️💻

Seja bem-vindo(a) ao **ForumHub**, uma API desenvolvida em **Java** utilizando o poderoso **Spring Framework**! Este projeto foi criado como uma simulação de um fórum de discussões para alunos da **Alura**, focando em boas práticas de desenvolvimento backend, autenticação JWT e persistência de dados com banco de dados relacional.

---

## 🛠️ Tecnologias e Dependências Utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3.4.1**: Framework para facilitar o desenvolvimento backend.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **Spring Data JPA**: Integração com banco de dados.
- **Flyway**: Migração e versionamento de banco de dados.
- **MySQL**: Banco de dados relacional utilizado.
- **JSON Web Token (JWT)**: Para autenticação e controle de sessão.

---

## 🚀 Funcionalidades Principais

1. **Autenticação e Autorização:**
   - A API utiliza autenticação baseada em JWT.
   - Apenas usuários autenticados podem acessar as rotas protegidas.

2. **CRUD de Discussões e Respostas:**
   - Endpoints para criar, listar, atualizar e deletar tópicos e respostas no fórum.

3. **Validações:**
   - Campos obrigatórios e validações são tratados de forma automatizada com o `Spring Validation`.

---

## 📚 Como Usar a API

### Endpoints Disponíveis

#### 🔑 Autenticação
**POST** `/auth/login`  
**Descrição:** Faz login e retorna um token JWT válido.  
**Exemplo de Body:**
```json
{
  "email": "usuario@exemplo.com",
  "senha": "senha123"
}
```

