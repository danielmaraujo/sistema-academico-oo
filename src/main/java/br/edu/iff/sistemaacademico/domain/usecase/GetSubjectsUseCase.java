package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Subject;

import java.util.Collection;

public interface GetSubjectsUseCase {
    Collection<Subject> perform();
}
