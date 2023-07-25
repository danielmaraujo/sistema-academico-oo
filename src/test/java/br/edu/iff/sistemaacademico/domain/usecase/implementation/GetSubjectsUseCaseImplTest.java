package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectsUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
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

@SpringBootTest(classes = GetSubjectsUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GetSubjectsUseCaseImplTest {

    @MockBean
    private SubjectRepository repository;

    @Autowired
    private GetSubjectsUseCase getSubjectsUseCase;

    @Test
    public void testPerform() {
        // Given
        Collection<Subject> subjects = new ArrayList<>();
        Subject subject = new Subject();
        subjects.add(subject);

        when(repository.findAll()).thenReturn(subjects);

        // When
        Collection<Subject> result = getSubjectsUseCase.perform();

        // Then
        assertEquals(subjects, result);
        verify(repository, times(1)).findAll();
    }
}
