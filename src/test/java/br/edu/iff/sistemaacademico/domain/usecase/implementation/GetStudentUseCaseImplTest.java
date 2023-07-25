package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(classes = GetStudentUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GetStudentUseCaseImplTest {

    @MockBean
    private StudentRepository repository;

    @Autowired
    private GetStudentUseCase getStudentUseCase;

    @Test
    public void testPerform() {
        // Given
        String studentId = "123";
        Student student = new Student();
        student.setId(studentId);

        when(repository.findById(studentId)).thenReturn(student);
        // When
        Student result = getStudentUseCase.perform(studentId);

        // Then
        assertEquals(student, result);
        verify(repository, times(1)).findById(studentId);
    }
}
