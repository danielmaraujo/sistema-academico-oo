package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest(classes = SubjectRepositoryImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SubjectRepositoryImplTest {

    private SubjectRepositoryImpl subjectRepository;

    @BeforeEach
    public void setup() {
        subjectRepository = new SubjectRepositoryImpl();
    }

    @Test
    public void testFindByIdExistingSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");

        // Act
        subjectRepository.insert(subject);
        Subject result = subjectRepository.findById("1");

        // Assert
        Assertions.assertEquals(subject, result);
    }

    @Test
    public void testFindByIdNonExistingSubject() {
        // Act & Assert
        Assertions.assertNull(subjectRepository.findById("1"));
    }

    @Test
    public void testFindAll() {
        // Arrange
        Subject subject1 = new Subject();
        subject1.setId("1");
        subject1.setName("Math");
        Subject subject2 = new Subject();
        subject2.setId("2");
        subject2.setName("Programming");

        // Act
        subjectRepository.insert(subject1);
        subjectRepository.insert(subject2);
        Collection<Subject> result = subjectRepository.findAll();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(subject1));
        Assertions.assertTrue(result.contains(subject2));
    }

    @Test
    public void testInsert() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");

        // Act
        Subject result = subjectRepository.insert(subject);

        // Assert
        Assertions.assertEquals(subject, result);
        Assertions.assertTrue(subjectRepository.findById("1") != null);
    }

    @Test
    public void testInsertExistingSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");
        subjectRepository.insert(subject);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            subjectRepository.insert(subject);
        });
    }

    @Test
    public void testUpdateExistingSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");
        subjectRepository.insert(subject);

        // Act
        Subject updatedSubject = new Subject();
        updatedSubject.setId("1");
        updatedSubject.setName("Programming");
        Subject result = subjectRepository.update("1", updatedSubject);

        // Assert
        Assertions.assertEquals(updatedSubject, result);
        Assertions.assertEquals("Programming", subjectRepository.findById("1").getName());
    }

    @Test
    public void testUpdateNonExistingSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            subjectRepository.update("1", subject);
        });
    }

    @Test
    public void testDeleteExistingSubject() {
        // Arrange
        Subject subject = new Subject();
        subject.setId("1");
        subject.setName("Math");
        subjectRepository.insert(subject);

        // Act
        subjectRepository.delete("1");

        // Assert
        Assertions.assertNull(subjectRepository.findById("1"));
    }

    @Test
    public void testDeleteNonExistingSubject() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            subjectRepository.delete("1");
        });
    }

}
