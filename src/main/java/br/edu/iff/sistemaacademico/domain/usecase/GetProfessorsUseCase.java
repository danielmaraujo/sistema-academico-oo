package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Professor;

import java.util.Collection;

public interface GetProfessorsUseCase {
    Collection<Professor> perform();
}
