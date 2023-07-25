# Sistema Acadêmico
### Projeto para a disciplina Tópicos Avançados II

Este projeto pretende implementar uma api básica seguindo os conceitos de Clean Architecture, TDD e integração contínua
  

#### Entidades:
- Entity
  -	id : String
- Professor
	- username : string
	- password : string
    -	name : String
- Student *(extends Entity)*
    - name: String
	- startYear : integer
	- course : Course
- Subject *(extends Entity)*
    -	name : String
    -   courseLoad : integer
    -   professor : Professor
    -   course : Course
    -   students : Student[]
- Course *(extends Entity)*
 	-	name : String
