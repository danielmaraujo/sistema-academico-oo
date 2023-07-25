package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Student;

public interface GetStudentUseCase {
    Student perform(String id);
}
