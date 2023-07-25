package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.RemoveSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = RemoveSubjectStudentUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RemoveSubjectStudentUseCaseImplTest {

    @MockBean
    private SubjectRepository subjectRepository;

    @Autowired
    private RemoveSubjectStudentUseCase removeSubjectStudentUseCase;

    @Test
    public void testPerform() {
        // Given
        Subject subject = new Subject();
        subject.getStudents().add("studentId");

        when(subjectRepository.findById(anyString())).thenReturn(subject);

        // When
        removeSubjectStudentUseCase.perform("studentId", "subjectId");

        // Then
        assertFalse(subject.getStudents().contains("studentId"));
    }

    @Test
    public void testPerform_StudentNotFound() {
        // Given
        Subject subject = new Subject();

        when(subjectRepository.findById(anyString())).thenReturn(subject);

        // Then
        assertThrowsExactly(IllegalArgumentException.class, () -> removeSubjectStudentUseCase.perform("studentId", "subjectId"));
    }
}
