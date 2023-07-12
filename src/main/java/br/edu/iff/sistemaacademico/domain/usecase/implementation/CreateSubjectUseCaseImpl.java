package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.CreateSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateSubjectUseCaseImpl implements CreateSubjectUseCase {
    final SubjectRepository repository;
    final Validator validator;

    public CreateSubjectUseCaseImpl(SubjectRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public String perform(RequestSubject dto) {
        Subject newSubject = new Subject(dto);

        Set<ConstraintViolation<Subject>> violations = validator.validate(newSubject);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid subject");
        }

        return repository.insert(newSubject).getId();
    }
}
