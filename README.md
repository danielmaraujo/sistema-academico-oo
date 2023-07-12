# Sistema Acadêmico
### Projeto para a disciplina Tópicos Avançados II

Este projeto pretende implementar uma api básica com testes automatizados e integração contínua.

  

#### Entidades:
- Entity
  -	id : String
- User *(extends Entity)*
	- username : string
	- password : string
    -	name : String
- Student *(extends User)*
	- startYear : integer
	- course : Course
- Professor *(extends User)*
- Subject *(extends Entity)*
    -	name : String
    -   courseLoad : integer
    -   professor : Professor
    -   course : Course
    -   students : Student[]
- Course *(extends Entity)*
 	-	name : String