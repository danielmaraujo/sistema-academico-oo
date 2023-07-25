package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Course;

import java.util.Collection;

public interface GetCoursesUseCase {
    Collection<Course> perform();
}
