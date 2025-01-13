<div align="center">
  <a href="https://www.oracle.com/br/education/oracle-next-education/">
    <img src="https://img.shields.io/badge/challenge-oracle%20next%20education-blue" alt="Challenge Oracle Next Education">
  </a>
</div>
<div align="center">
    <img src="https://img.shields.io/badge/finished-january-green" alt="Finished in January">
</div>

---

# ForumHub - API de Simulação de Fórum 🗣️💻

Seja bem-vindo(a) ao **ForumHub**, uma API desenvolvida em **Java** utilizando o poderoso **Spring Framework**! Este projeto foi criado como uma simulação de um fórum de discussões para alunos da **Alura**, focando em boas práticas de desenvolvimento backend, autenticação JWT e persistência de dados com banco de dados relacional.

---

## 🛠️ Tecnologias e dependências utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3.4.1**: Framework para facilitar o desenvolvimento backend.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **Spring Data JPA**: Integração com banco de dados.
- **Flyway**: Migração e versionamento de banco de dados.
- **MySQL**: Banco de dados relacional utilizado.
- **JSON Web Token (JWT)**: Para autenticação e controle de sessão.

---

## 🚀 Principais funcionalidades

1. **Autenticação e autorização:**
   - A API utiliza autenticação baseada em JWT.
   - Apenas usuários autenticados podem acessar as rotas protegidas.

2. **CRUD de tópicos e respostas:**
   - Endpoints para criar, listar, atualizar e deletar tópicos e respostas no fórum.

3. **Validações:**
   - Campos obrigatórios e validações são tratados de forma automatizada com o `Spring Validation`.

---

## 📚 Como usar a API

### Endpoints disponíveis

### 🔒 **Rotas de usuários**
- **POST** `/usuarios/login`  
  **Descrição:** Autentica um usuário com e-mail e senha, retornando um token JWT.  
  **Requer autenticação:** Não.  
  **Corpo da requisição:**
  ```json
  {
  "email": "usuario@email.com",
  "senha": "12345678"
  }
  ```
  **Corpo da resposta:**
  ```json
  {
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3Mi..."
  }
  ```
  
- **POST** `/usuarios/cadastro`  
  **Descrição:** Realiza o cadastro de um novo usuário no sistema, criptografando a senha.  
  **Requer autenticação:** Não.  
  **Corpo da requisição:** Objeto `UsuarioCadastroDTO`.

---

### 📝 **Rotas de tópicos**
- **POST** `/topicos`  
  **Descrição:** Cria um novo tópico com base no título, mensagem e curso informados.  
  **Requer autenticação:** Sim.  
  **Corpo da requisição:** Objeto `TopicoPostagemDTO`.

- **GET** `/topicos`  
  **Descrição:** Lista todos os tópicos disponíveis no fórum.  
  **Requer autenticação:** Não.

- **GET** `/topicos/{id}`  
  **Descrição:** Exibe os detalhes de um tópico específico pelo ID informado.  
  **Requer autenticação:** Não.

- **PUT** `/topicos/{id}`  
  **Descrição:** Atualiza um tópico específico (ID) caso o autor seja o mesmo do token.  
  **Requer autenticação:** Sim.  
  **Corpo da requisição:** Objeto `TopicoAtualizadoDTO`.

- **DELETE** `/topicos/{id}`  
  **Descrição:** Remove um tópico específico (ID) caso o autor seja o mesmo do token.  
  **Requer autenticação:** Sim.

---

### 🛠 **Rotas de respostas**
- **POST** `/respostas/{id}`  
  **Descrição:** Adiciona uma nova resposta ao tópico especificado pelo ID.  
  **Requer autenticação:** Sim.  
  **Corpo da requisição:** Objeto `RespostaDTO` válido.

- **GET** `/respostas/{id}`  
  **Descrição:** Lista todas as respostas relacionadas ao tópico especificado pelo ID.  
  **Requer autenticação:** Não.

- **PUT** `/respostas/{id}`  
  **Descrição:** Atualiza uma resposta específica (ID) caso o autor seja o mesmo do token.  
  **Requer autenticação:** Sim.  
  **Corpo da requisição:** Objeto `RespostaAtualizadaDTO`.

- **DELETE** `/respostas/{id}`  
  **Descrição:** Remove uma resposta específica (ID) caso o autor seja o mesmo do token.  
  **Requer autenticação:** Sim.

## 🔑 Segurança
Todos os endpoints protegidos requerem o envio do cabeçalho Authorization com o token JWT no formato:

```
Authorization: Bearer {seu_token_jwt}
```

## 📝 Sobre o projeto

Este backend foi desenvolvido com o objetivo de consolidar conhecimentos em desenvolvimento de **APIs RESTful**, utilizando boas práticas de segurança e organização de código, e faz parte do challenge de conclusão da formação **Java e Spring Framework** da **Oracle Next Education**. O foco foi criar uma aplicação limpa, funcional e escalável.

Sinta-se à vontade para explorar o repositório, contribuir e deixar feedback! 

