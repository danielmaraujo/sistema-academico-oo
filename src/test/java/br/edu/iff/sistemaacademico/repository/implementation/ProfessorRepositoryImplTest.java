package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest(classes = ProfessorRepositoryImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ProfessorRepositoryImplTest {

    private ProfessorRepositoryImpl professorRepository;

    @BeforeEach
    public void setup() {
        professorRepository = new ProfessorRepositoryImpl();
    }

    @Test
    public void testFindByIdExistingProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");

        // Act
        professorRepository.insert(professor);
        Professor result = professorRepository.findById("1");

        // Assert
        Assertions.assertEquals(professor, result);
    }

    @Test
    public void testFindByIdNonExistingProfessor() {
        // Act & Assert
        Assertions.assertNull(professorRepository.findById("1"));
    }

    @Test
    public void testFindAll() {
        // Arrange
        Professor professor1 = new Professor();
        professor1.setId("1");
        professor1.setName("Fernando");
        Professor professor2 = new Professor();
        professor2.setId("2");
        professor2.setName("Fabio");

        // Act
        professorRepository.insert(professor1);
        professorRepository.insert(professor2);
        Collection<Professor> result = professorRepository.findAll();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(professor1));
        Assertions.assertTrue(result.contains(professor2));
    }

    @Test
    public void testInsert() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");

        // Act
        Professor result = professorRepository.insert(professor);

        // Assert
        Assertions.assertEquals(professor, result);
        Assertions.assertTrue(professorRepository.findById("1") != null);
    }

    @Test
    public void testInsertExistingProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");
        professorRepository.insert(professor);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            professorRepository.insert(professor);
        });
    }

    @Test
    public void testUpdateExistingProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");
        professorRepository.insert(professor);

        // Act
        Professor updatedProfessor = new Professor();
        updatedProfessor.setId("1");
        updatedProfessor.setName("Fabio");
        Professor result = professorRepository.update("1", updatedProfessor);

        // Assert
        Assertions.assertEquals(updatedProfessor, result);
        Assertions.assertEquals("Fabio", professorRepository.findById("1").getName());
    }

    @Test
    public void testUpdateNonExistingProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            professorRepository.update("1", professor);
        });
    }

    @Test
    public void testDeleteExistingProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setId("1");
        professor.setName("Fernando");
        professorRepository.insert(professor);

        // Act
        professorRepository.delete("1");

        // Assert
        Assertions.assertNull(professorRepository.findById("1"));
    }

    @Test
    public void testDeleteNonExistingProfessor() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            professorRepository.delete("1");
        });
    }

}


