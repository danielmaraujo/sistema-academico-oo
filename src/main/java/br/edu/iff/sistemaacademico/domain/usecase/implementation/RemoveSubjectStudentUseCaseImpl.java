package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.RemoveSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveSubjectStudentUseCaseImpl implements RemoveSubjectStudentUseCase {
    final SubjectRepository subjectRepository;

    public RemoveSubjectStudentUseCaseImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void perform(String studentId, String subjectId) {
        Subject subject = subjectRepository.findById(subjectId);

        if(subject.getStudents().contains(studentId)){
            subject.getStudents().remove(studentId);
        }else{
            throw new IllegalArgumentException("Estudante não encontrado na disciplina");
        }
    }
}
