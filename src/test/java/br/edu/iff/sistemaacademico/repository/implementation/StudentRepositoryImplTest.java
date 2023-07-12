package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest(classes = StudentRepositoryImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class StudentRepositoryImplTest {

    private StudentRepositoryImpl studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository = new StudentRepositoryImpl();
    }

    @Test
    public void testFindByIdExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");

        // Act
        studentRepository.insert(student);
        Student result = studentRepository.findById("1");

        // Assert
        Assertions.assertEquals(student, result);
    }

    @Test
    public void testFindByIdNonExistingStudent() {
        // Act & Assert
        Assertions.assertNull(studentRepository.findById("1"));
    }

    @Test
    public void testFindAll() {
        // Arrange
        Student student1 = new Student();
        student1.setId("1");
        student1.setName("Daniel");
        Student student2 = new Student();
        student2.setId("2");
        student2.setName("Fernando");

        // Act
        studentRepository.insert(student1);
        studentRepository.insert(student2);
        Collection<Student> result = studentRepository.findAll();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(student1));
        Assertions.assertTrue(result.contains(student2));
    }

    @Test
    public void testInsert() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");

        // Act
        Student result = studentRepository.insert(student);

        // Assert
        Assertions.assertEquals(student, result);
        Assertions.assertTrue(studentRepository.findById("1") != null);
    }

    @Test
    public void testInsertExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");
        studentRepository.insert(student);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studentRepository.insert(student);
        });
    }

    @Test
    public void testUpdateExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");
        studentRepository.insert(student);

        // Act
        Student updatedStudent = new Student();
        updatedStudent.setId("1");
        updatedStudent.setName("Fernando");
        Student result = studentRepository.update("1", updatedStudent);

        // Assert
        Assertions.assertEquals(updatedStudent, result);
        Assertions.assertEquals("Fernando", studentRepository.findById("1").getName());
    }

    @Test
    public void testUpdateNonExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studentRepository.update("1", student);
        });
    }

    @Test
    public void testDeleteExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId("1");
        student.setName("Daniel");
        studentRepository.insert(student);

        // Act
        studentRepository.delete("1");

        // Assert
        Assertions.assertNull(studentRepository.findById("1"));
    }

    @Test
    public void testDeleteNonExistingStudent() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studentRepository.delete("1");
        });
    }

}
