package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.UpdateSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UpdateSubjectUseCaseImpl implements UpdateSubjectUseCase {
    final SubjectRepository repository;
    final Validator validator;

    public UpdateSubjectUseCaseImpl(SubjectRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public String perform(String id, RequestSubject dto) {
        Subject subject = new Subject(dto);
        subject.setId(id);

        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Dados incompletos");
        }

        return repository.update(id, subject).getId();
    }
}
