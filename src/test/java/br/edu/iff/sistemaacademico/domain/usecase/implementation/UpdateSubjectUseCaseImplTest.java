package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.UpdateSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(classes = UpdateSubjectUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UpdateSubjectUseCaseImplTest {

    @MockBean
    private SubjectRepository repository;

    @Autowired
    private UpdateSubjectUseCase updateSubjectUseCase;

    @Test
    public void testPerform() {
        // Given
        String id = "subjectId";
        RequestSubject requestSubject = new RequestSubject("Updated Subject", 80, "123", "321");

        Subject updatedSubject = new Subject(requestSubject);
        updatedSubject.setId(id);

        when(repository.update(eq(id), any(Subject.class))).thenReturn(updatedSubject);

        // When
        String result = updateSubjectUseCase.perform(id, requestSubject);

        // Then
        verify(repository, times(1)).update(eq(id), any(Subject.class));
        assertEquals(updatedSubject.getId(), result);
    }

    @Test
    public void testPerform_missingFields() {
        // Given
        String id = "subjectId";
        RequestSubject requestSubject = new RequestSubject(null, null, null, null);
        Subject subject = new Subject(requestSubject);
        subject.setId(id);

        // Then
        assertThrows(IllegalArgumentException.class, () -> updateSubjectUseCase.perform(id, requestSubject));
        verify(repository, never()).update(eq(id), any(Subject.class));
    }
}
