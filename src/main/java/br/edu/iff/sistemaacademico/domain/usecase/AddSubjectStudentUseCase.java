package br.edu.iff.sistemaacademico.domain.usecase;

public interface AddSubjectStudentUseCase {
    void perform(String studentId, String subjectId);
}
