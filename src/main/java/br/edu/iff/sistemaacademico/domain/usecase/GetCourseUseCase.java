package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.entity.Course;

public interface GetCourseUseCase {
    Course perform(String id);
}
