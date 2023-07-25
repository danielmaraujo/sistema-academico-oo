package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentsUseCase;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(classes = GetStudentsUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GetStudentsUseCaseImplTest {

    @MockBean
    private StudentRepository repository;

    @Autowired
    private GetStudentsUseCase getStudentsUseCase;

    @Test
    public void testPerform() {
        // Given
        Collection<Student> students = new ArrayList<>();
        Student student = new Student();
        students.add(student);

        when(repository.findAll()).thenReturn(students);

        // When
        Collection<Student> result = getStudentsUseCase.perform();

        // Then
        assertEquals(students, result);
        verify(repository, times(1)).findAll();
    }
}
