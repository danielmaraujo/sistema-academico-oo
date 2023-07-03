# Sistema Acadêmico
### Projeto para a disciplina Tópicos Avançados II

Este projeto pretende implementar uma api básica com testes automatizados e integração contínua.

  

#### Entidades:
- User *(extends Entity)*
 	-	id : String
	- username : string
	- password : string
    -	name : String
- Student *(extends User)*
	- startYear : integer
	- course : Course
- Professor *(extends User)*
- Subject *(extends Entity)*
 	-	id : String
    -	name : String
    -   courseLoad : integer
    -   professor : Professor
    -   course : Course
    -   students : Student[]
- Course *(extends Entity)*
 	-	id : String
 	-	name : String