package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.DeleteSubjectUseCase;
import br.edu.iff.sistemaacademico.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(classes = DeleteSubjectUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DeleteSubjectUseCaseImplTest {

    @MockBean
    private SubjectRepository repository;

    @Autowired
    private DeleteSubjectUseCase deleteSubjectUseCase;

    @Test
    public void testPerform() {
        // Given
        String id = "subjectId";
        String professor = "professorId";

        Subject subject = new Subject();
        subject.setProfessor(professor);

        when(repository.findById(eq(id))).thenReturn(subject);

        // When
        deleteSubjectUseCase.perform(id, professor);

        // Then
        verify(repository, times(1)).delete(eq(id));
    }

    @Test
    public void testPerform_SubjectNotFromProfessor() {
        // Given
        String id = "subjectId";
        String professor = "professorId";

        Subject subject = new Subject();
        subject.setProfessor("anotherProfessorId");

        when(repository.findById(eq(id))).thenReturn(subject);

        // Then
        assertThrowsExactly(IllegalArgumentException.class, () -> deleteSubjectUseCase.perform(id, professor));
    }
}
