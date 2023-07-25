package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Student;

import java.util.Collection;

public interface GetStudentsUseCase {
    Collection<Student> perform();
}
