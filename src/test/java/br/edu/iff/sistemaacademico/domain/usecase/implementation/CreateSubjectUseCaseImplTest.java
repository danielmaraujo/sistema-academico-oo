package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.CreateSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@SpringBootTest(classes = CreateSubjectUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CreateSubjectUseCaseImplTest {

    @MockBean
    private SubjectRepository repository;

    @Autowired
    private CreateSubjectUseCase createSubjectUseCase;

    @Test
    public void testPerform() {
        // Given
        RequestSubject requestSubject = new RequestSubject("Updated Subject", 80, "123", "321");
        Subject savedSubject = new Subject(requestSubject);
        savedSubject.setId(UUID.randomUUID().toString());

        when(repository.insert(any(Subject.class))).thenReturn(savedSubject);

        // When
        String result = createSubjectUseCase.perform(requestSubject);

        // Then
        verify(repository, times(1)).insert(any(Subject.class));
        assertEquals(savedSubject.getId(), result);
    }

    @Test
    public void testPerform_missingFields() {
        RequestSubject requestSubject = new RequestSubject(null, null, null, null);
        assertThrowsExactly(IllegalArgumentException.class, () -> createSubjectUseCase.perform(requestSubject));
    }
}
