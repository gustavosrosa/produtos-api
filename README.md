# API de Produtos
Projeto em Spring Boot para criação de lista de produtos

Esse projeto foi criado para revisão de pontos importantes do framework Spring, usando Maven como gerenciador de dependências e JUnit na manutenibilidade de testes unitários.

Versões:

- Java: 21
- Maven: 3+

Dependências

- Spring DevTools
- Spring Data JPA
- Lombok
- H2 database
- Jupiter, JUnit
- Spring Web

Endpoints:

- POST: Cadastrar: /api/produto

		Request Body (sendo descrição não obrigatório):
		{
		    "nome": String,
		    "descricao": String,
		    "preco": BigDecimal
		}
		
		Sucesso: 201 Created

- PUT: Modificar: /api/produto/{id}

		Request Body (sendo todos não obrigatórios):
		{
		    "nome": String,
		    "descricao": String,
		    "preco": BigDecimal
		}

		Sucesso: 200 OK

- GET: Buscar por ID: /api/produto/{id}
- GET: Buscar Lista de Produtos: /api/produto
- DELETE: Remover: /api/produto/{id}

Qualquer sugestão, comentário... Fique à vontade!

No meu perfil tem todos os meus links, entre em contato comigo! Serei grato em recepcioná-lo(a)!
