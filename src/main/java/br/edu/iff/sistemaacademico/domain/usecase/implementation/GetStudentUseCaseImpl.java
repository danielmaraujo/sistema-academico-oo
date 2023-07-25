package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class GetStudentUseCaseImpl implements GetStudentUseCase {
    final StudentRepository repository;

    public GetStudentUseCaseImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student perform(String id) {
        return repository.findById(id);
    }
}
