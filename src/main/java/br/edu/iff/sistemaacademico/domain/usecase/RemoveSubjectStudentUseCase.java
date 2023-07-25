package br.edu.iff.sistemaacademico.domain.usecase;

public interface RemoveSubjectStudentUseCase {
    void perform(String studentId, String subjectId);
}
