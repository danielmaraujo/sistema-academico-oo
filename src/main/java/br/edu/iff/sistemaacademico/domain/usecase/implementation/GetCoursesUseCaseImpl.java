package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.GetCoursesUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectsUseCase;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetCoursesUseCaseImpl implements GetCoursesUseCase {
    final CourseRepository repository;

    public GetCoursesUseCaseImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Course> perform() {
        return repository.findAll();
    }
}
