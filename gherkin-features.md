## Feature: Inclusão de disciplina
    Como estudante
    Quero incluir uma disciplina 
    Para cursá-la.
  
### Scenario: Inclusão válida
    Dado que a disciplina existe
    E corresponde ao curso do estudante
    Quando ele solicita a inclusão
    Então ele é adicionado na lista de alunos

### Scenario: Disciplina de outro curso
    Dado que a disciplina existe
    E não corresponde ao curso do estudante
    Quando ele solicita a inclusão
    Então ele é informado que não pode se inscrever naquela disciplina

### Scenario: Disciplina não existe
    Dado que a disciplina não existe
    Quando ele solicita a inclusão
    Então ele é informado que aquela disciplina não existe

## Feature: Exclusão de disciplina
    Como estudante
    Quero excluir uma disciplina 
    Para deixar de cursá-la.
  
### Scenario: Exclusão válida
    Dado que a disciplina existe
    E o estudante está inscrito na disciplina
    Quando ele solicita a exclusão
    Então ele é removido da lista de alunos

### Scenario: Estudante não está inscrito
    Dado que a disciplina existe
    E o estudante não está inscrito na disciplina
    Quando ele solicita a inclusão
    Então ele é informado que não está inscrito

### Scenario: Disciplina não existe
    Dado que a disciplina não existe
    Quando ele solicita a exclusão
    Então ele é informado que aquela disciplina não existe

## Feature: Criação de disciplina
    Como professor
    Quero criar uma disciplina 
    Para disponibilizar para estudantes.
  
### Scenario: Criação válida
    Dado que o professor preenche todos os campos necessários
    Quando ele solicita a criação
    Então a disciplina é criada

### Scenario: Criação inválida
    Dado que o professor não preenche todos os campos necessários
    Quando ele solicita a criação
    Então ele é informado que precisa preencher os campos

## Feature: Alteração de disciplina
    Como professor
    Quero alterar uma disciplina 
    Para corrigir ou adequar alguma informação.
  
### Scenario: Alteração válida
    Dado que a disciplina existe
    E pertence ao professor
    Quando ele solicita a alteração
    Então a disciplina é alterada

### Scenario: Disciplina não pertence ao professor
    Dado que a disciplina existe
    E não pertence ao professor
    Quando ele solicita a alteração
    Então ele é informado que não pode alterar aquela disciplina

### Scenario: Disciplina não existe
    Dado que a disciplina não existe
    Quando ele solicita a alteração
    Então ele é informado que aquela disciplina não existe

## Feature: Deleção de disciplina
    Como professor
    Quero deletar uma disciplina 
    Para que não apareça no sistema.
  
### Scenario: Deleção válida
    Dado que a disciplina existe
    E pertence ao professor
    Quando ele solicita a deleção
    Então a disciplina é deletada

### Scenario: Disciplina não pertence ao professor
    Dado que a disciplina existe
    E não pertence ao professor
    Quando ele solicita a deleção
    Então ele é informado que não pode deletar aquela disciplina

### Scenario: Disciplina não existe
    Dado que a disciplina não existe
    Quando ele solicita a deleção
    Então ele é informado que aquela disciplina não existe
    