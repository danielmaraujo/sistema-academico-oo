package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class GetSubjectUseCaseImpl implements GetSubjectUseCase {
    final SubjectRepository repository;

    public GetSubjectUseCaseImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject perform(String id) {
        return repository.findById(id);
    }
}
