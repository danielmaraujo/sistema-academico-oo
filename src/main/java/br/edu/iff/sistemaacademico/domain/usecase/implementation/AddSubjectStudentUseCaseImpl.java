package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.AddSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class AddSubjectStudentUseCaseImpl implements AddSubjectStudentUseCase {
    final SubjectRepository subjectRepository;
    final StudentRepository studentRepository;

    public AddSubjectStudentUseCaseImpl(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void perform(String studentId, String subjectId) {
        Subject subject = subjectRepository.findById(subjectId);

        if(studentRepository.findById(studentId) != null){
            subject.getStudents().add(studentId);
        }else{
            throw new IllegalArgumentException("Estudante não encontrado");
        }
    }
}
