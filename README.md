<h1 align="center">ForumHub</h1>


## *📖 Descrição do Projeto*

O ForumHub é uma API REST desenvolvida em Java com o framework Spring Boot. O objetivo do projeto é criar um backend robusto para um fórum de discussão, permitindo o gerenciamento de tópicos, usuários e autenticação através de tokens JWT.

## ✨ *Funcionalidades*
A API oferece as seguintes funcionalidades principais:

- Autenticação de Usuários: Autenticação segura com login e senha, retornando um token JWT.

- Gestão de Tópicos (CRUD):

- Criar: Adicionar novos tópicos de discussão.

- Listar: Visualizar todos os tópicos existentes.

- Buscar: Encontrar um tópico específico por ID.

- Atualizar: Modificar informações de um tópico.

- Deletar: Excluir um tópico.

- Autorização: Todos os endpoints de tópicos são protegidos e requerem um token JWT válido no cabeçalho Authorization.


## *🛠️ Tecnologias Utilizadas*


- Java: Linguagem de programação.

- Spring Boot: Framework para simplificar o desenvolvimento de aplicações Java.

- Spring Data JPA: Para persistência de dados e interação com o banco de dados.

- MySQL: Banco de dados relacional para armazenar os dados da aplicação.

- Flyway: Ferramenta para gerenciar migrações de banco de dados de forma automática.

- Spring Security: Para implementar autenticação e autorização com JWT.

- JWT (Java JWT): Biblioteca para gerar e validar tokens de segurança.

- Maven: Gerenciador de dependências e de build do projeto.

- Insomnia: Ferramentas para testar os endpoints da API.


## *📁 Estrutura do Projeto*


      src/main/java
      ├── br/com/alura/ForumHub
      │   ├── controller
      │   │   ├── AuthenticationController.java
      │   │   └── TopicoController.java
      │   ├── domain
      │   │   ├── topico
      │   │   │   ├── Topico.java
      │   │   │   └── TopicoRepository.java
      │   │   └── usuario
      │   │       ├── Usuario.java
      │   │       └── UsuarioRepository.java
      │   └── infra
      │       └── security
      │           ├── DadosToken.java
      │           ├── SecurityConfigurations.java
      │           ├── SecurityFilter.java
      │           └── TokenService.java
      

## *📥 Como Executar*

### Clone o repositório:

- git clone https://github.com/seu-usuario/ForumHub.git
cd ForumHub

- Configurar o Banco de Dados:

- Crie um banco de dados MySQL chamado forum_db.

- Edite o arquivo src/main/resources/application.properties com suas credenciais de banco de dados.

### Executar a Aplicação:

- Compile e execute a aplicação usando Maven ou sua IDE:

- mvn spring-boot:run

## *➡️ Endpoints da API*

- Login (para obter o token JWT):

- POST /login

- Body: { "login": "seu_login", "senha": "sua_senha" }

- Tópicos (requer token JWT no cabeçalho Authorization):

- POST /topicos (Criar)

- GET /topicos (Listar)

- GET /topicos/{id} (Detalhar)

- PUT /topicos (Atualizar)

- DELETE /topicos/{id} (Deletar)
