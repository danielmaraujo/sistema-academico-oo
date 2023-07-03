package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;

import java.util.UUID;

public interface CreateSubjectUseCase {
    public UUID perform(RequestSubject dto);
}
