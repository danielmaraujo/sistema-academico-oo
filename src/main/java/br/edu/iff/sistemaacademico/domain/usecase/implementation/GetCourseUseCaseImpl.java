package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.usecase.GetCourseUseCase;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCourseUseCaseImpl implements GetCourseUseCase {
    final CourseRepository repository;

    public GetCourseUseCaseImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course perform(String id) {
        return repository.findById(id);
    }
}
