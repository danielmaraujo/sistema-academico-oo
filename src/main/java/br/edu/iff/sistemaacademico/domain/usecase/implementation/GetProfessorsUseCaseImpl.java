package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Professor;
import br.edu.iff.sistemaacademico.domain.usecase.GetProfessorsUseCase;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
import br.edu.iff.sistemaacademico.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetProfessorsUseCaseImpl implements GetProfessorsUseCase {
    final ProfessorRepository repository;

    public GetProfessorsUseCaseImpl(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Professor> perform() {
        return repository.findAll();
    }
}
