package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentsUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetStudentsUseCaseImpl implements GetStudentsUseCase {
    final StudentRepository repository;

    public GetStudentsUseCaseImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Student> perform() {
        return repository.findAll();
    }
}
