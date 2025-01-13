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
  **Descrição:** Autentica um usuário com e-mail e senha, retornando um token JWT que deve ser usado como Bearer Token nas requisições que precisam de autenticação.  
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
  **Corpo da requisição:**
  ```json
	{
	"username" : "usuario",
	"email": "usuario@email.com",
	"senha": "12345678"
	}
  ```
  **Corpo da resposta:**
  ```json
	{
	"username" : "usuario",
	"email": "usuario@email.com",
	"senha": "12345678"
	}
  ```
  
---

### 📝 **Rotas de tópicos**
- **POST** `/topicos`  
  **Descrição:** Cria um novo tópico com base no título, mensagem e curso informados.   
  **Corpo da requisição:**
  ```json
	{
	"titulo" : "Título do tópico",
	"mensagem": "Estou escrevendo sobre minha dúvida...",
	"curso": "Java e Spring Framework"
	}
  ```
  **Corpo da resposta:**
  ```json
	{
	"id" : 1,
	"titulo" : "Título do tópico",
	"mensagem": "Estou escrevendo sobre minha dúvida...",
	"dataCriacao" : "2025-01-11T21:07:48.5402988",
	"autor" : {
		"username" : "usuario"
  	},
	"curso": "Java e Spring Framework"
	}
  ```

- **GET** `/topicos`  
  **Descrição:** Lista todos os tópicos disponíveis no fórum.   
  **Corpo da resposta:**
  ```json
	[
		{
		"id" : 1,
		"titulo" : "Título do tópico",
		"mensagem": "Estou escrevendo sobre minha dúvida...",
		"dataCriacao" : "2025-01-11T21:07:48.5402988",
		"autor" : {
			"username" : "usuario"
	  	},
		"curso": "Java e Spring Framework"
		},
		{
		"id" : 2,
		"titulo" : "Título do segundo tópico",
		"mensagem": "Estou escrevendo sobre minha segunda dúvida...",
		"dataCriacao" : "2025-01-11T22:07:48.5402988",
		"autor" : {
			"username" : "usuario"
	  	},
		"curso": "Java e Spring Framework"
		}
  
	]
  ```

- **GET** `/topicos/{id}`  
  **Descrição:** Exibe os detalhes de um tópico específico pelo ID informado.   
  **Corpo da resposta:**
  ```json
	{
	"id" : 1,
	"titulo" : "Título do tópico",
	"mensagem": "Estou escrevendo sobre minha dúvida...",
	"dataCriacao" : "2025-01-11T21:07:48.5402988",
	"autor" : {
		"username" : "usuario"
  	},
	"curso": "Java e Spring Framework"
	}
  ```

- **PUT** `/topicos/{id}`  
  **Descrição:** Atualiza um tópico específico (ID) caso o autor seja o mesmo do token.  
  **Corpo da resposta:**
  ```json
	{
	"titulo" : "Título do tópico editado",
	"mensagem": "Mensagem editada",
	}
  ```

- **DELETE** `/topicos/{id}`  
  **Descrição:** Remove um tópico específico (ID) caso o autor seja o mesmo do token.  
  **Corpo da resposta:**
  ```json
	Tópico de id 1 deletado.
  ```

---

### 🛠 **Rotas de respostas**
- **POST** `/respostas/{id}`  
  **Descrição:** Adiciona uma nova resposta ao tópico especificado pelo ID.   
  **Corpo da requisição:**
```json
	{
	"mensagem" : "Respondendo meu próprio tópico..."
	}
```
  **Corpo da resposta:**
```json
	{
	"id": 1,
	"mensagem": "Respondendo meu próprio tópico...",
	"data": "2025-01-11T23:51:30.3373134",
	"autor": {
		"username": "usuario"
		},
	"topico": {
		"id": 1,
		"titulo": "Título do tópico",
		"mensagem": "Estou escrevendo sobre minha dúvida...",
		"dataCriacao": "2025-01-11T16:38:15",
		"autor": {
			"username": "usuario"
			},
		"curso": "Java e Spring Framework"
		}
	}
```

- **GET** `/respostas/{id}`  
  **Descrição:** Lista todas as respostas relacionadas ao tópico especificado pelo ID.
  **Corpo da resposta:**
```json
	[
		{
		"id": 1,
		"mensagem": "Respondendo meu próprio tópico...",
		"data": "2025-01-11T23:51:30.3373134",
		"autor": {
			"username": "usuario"
			},
		"topico": {
			"id": 1,
			"titulo": "Título do tópico",
			"mensagem": "Estou escrevendo sobre minha dúvida...",
			"dataCriacao": "2025-01-11T16:38:15",
			"autor": {
				"username": "usuario"
				},
			"curso": "Java e Spring Framework"
			}
		},
		{
		"id": 2,
		"mensagem": "Respondendo meu próprio tópico de novo...",
		"data": "2025-01-11T23:54:30.3373134",
		"autor": {
			"username": "usuario"
			},
		"topico": {
			"id": 1,
			"titulo": "Título do tópico",
			"mensagem": "Estou escrevendo sobre minha dúvida...",
			"dataCriacao": "2025-01-11T16:38:15",
			"autor": {
				"username": "usuario"
				},
			"curso": "Java e Spring Framework"
			}
		}
	]
```

- **PUT** `/respostas/{id}`  
  **Descrição:** Atualiza uma resposta específica (ID) caso o autor seja o mesmo do token.  
  **Corpo da requisição:** 
```json
	{
		"mensagem" : "Atualizando minha resposta..."
	}
```
  **Corpo da resposta:** 
```json
	{
	"id": 1,
	"mensagem": "Atualizando minha resposta...",
	"data": "2025-01-11T23:51:30.3373134",
	"autor": {
		"username": "usuario"
		},
	"topico": {
		"id": 1,
		"titulo": "Título do tópico",
		"mensagem": "Estou escrevendo sobre minha dúvida...",
		"dataCriacao": "2025-01-11T16:38:15",
		"autor": {
			"username": "usuario"
			},
		"curso": "Java e Spring Framework"
		}
	}
```

- **DELETE** `/respostas/{id}`  
  **Descrição:** Remove uma resposta específica (ID) caso o autor seja o mesmo do token.
    **Corpo da resposta:**
  ```json
	Resposta de id 1 deletada.
  ```

## 🔑 Segurança
Todos os endpoints protegidos requerem o envio do cabeçalho Authorization com o token JWT no formato:

```
Authorization: Bearer {seu_token_jwt}
```

## 📝 Sobre o projeto

Este backend foi desenvolvido com o objetivo de consolidar conhecimentos em desenvolvimento de **APIs RESTful**, utilizando boas práticas de segurança e organização de código, e faz parte do challenge de conclusão da formação **Java e Spring Framework** da **Oracle Next Education**. O foco foi criar uma aplicação limpa, funcional e escalável.

Sinta-se à vontade para explorar o repositório, contribuir e deixar feedback! 

