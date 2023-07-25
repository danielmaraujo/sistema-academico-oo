package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;

public interface UpdateSubjectUseCase {
    String perform(String id, RequestSubject dto);
}
