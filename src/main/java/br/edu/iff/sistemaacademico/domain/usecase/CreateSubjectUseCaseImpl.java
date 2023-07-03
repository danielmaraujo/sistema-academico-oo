package br.edu.iff.sistemaacademico.domain.usecase;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateSubjectUseCaseImpl implements CreateSubjectUseCase{
    final
    SubjectRepository repository;

    public CreateSubjectUseCaseImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID perform(RequestSubject dto) {
        Subject newSubject = new Subject(dto);
        return repository.save(newSubject).getId();
    }
}
