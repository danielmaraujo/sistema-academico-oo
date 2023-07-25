package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentsUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectsUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetSubjectsUseCaseImpl implements GetSubjectsUseCase {
    final SubjectRepository repository;

    public GetSubjectsUseCaseImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Subject> perform() {
        return repository.findAll();
    }
}
