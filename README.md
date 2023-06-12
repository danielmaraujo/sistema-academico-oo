# Sistema Acadêmico
### Projeto para a disciplina Tópicos Avançados II

Este projeto pretende implementar uma api básica com testes automatizados e integração contínua.

  

#### Entidades:
- Entity
	-	id : String
	-	name : String
- User *(extends Entity)*
	- username : string
	- password : string
- Student *(extends User)*
	- startYear : integer
	- course : Course
- Professor *(extends User)*

- Subject *(extends Entity)*
	- courseLoad : integer
	- professor : Professor
	- course : Course
	- students : Student[]
- Course *(extends Entity)*