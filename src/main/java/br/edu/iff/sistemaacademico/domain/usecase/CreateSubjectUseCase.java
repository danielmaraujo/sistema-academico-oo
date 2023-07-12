package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;

public interface CreateSubjectUseCase {
    String perform(RequestSubject dto);
}
