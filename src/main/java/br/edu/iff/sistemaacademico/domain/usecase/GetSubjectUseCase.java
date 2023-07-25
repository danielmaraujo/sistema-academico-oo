package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Subject;

public interface GetSubjectUseCase {
    Subject perform(String id);
}
