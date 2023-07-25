package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.AddSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AddSubjectStudentUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class AddSubjectStudentUseCaseImplTest {

    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private AddSubjectStudentUseCase addSubjectStudentUseCase;

    @Test
    public void testPerform() {
        // Given
        Subject subject = new Subject();

        when(subjectRepository.findById(anyString())).thenReturn(subject);
        when(studentRepository.findById(anyString())).thenReturn(new Student());

        // When
        addSubjectStudentUseCase.perform("studentId", "subjectId");

        // Then
        assertTrue(subject.getStudents().contains("studentId"));
    }

    @Test
    public void testPerform_StudentNotFound() {
        // Given
        Subject subject = new Subject();

        when(subjectRepository.findById(anyString())).thenReturn(subject);
        when(studentRepository.findById(anyString())).thenReturn(null);

        // Then
        assertThrowsExactly(IllegalArgumentException.class, () -> addSubjectStudentUseCase.perform("studentId", "subjectId"));
    }
}
