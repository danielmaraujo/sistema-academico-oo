package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.usecase.DeleteSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteSubjectUseCaseImpl implements DeleteSubjectUseCase {
    final SubjectRepository repository;

    public DeleteSubjectUseCaseImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void perform(String id, String professor) {
        if(repository.findById(id).getProfessor().equals(professor)){
            repository.delete(id);
        }else {
            throw new IllegalArgumentException("Disciplina não pertence a esse professor");
        }
    }
}
