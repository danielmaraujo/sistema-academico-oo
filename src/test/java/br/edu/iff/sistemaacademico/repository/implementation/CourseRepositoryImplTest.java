package br.edu.iff.sistemaacademico.repository.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest(classes = CourseRepositoryImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CourseRepositoryImplTest {

    private CourseRepositoryImpl courseRepository;

    @BeforeEach
    public void setup() {
        courseRepository = new CourseRepositoryImpl();
    }

    @Test
    public void testFindByIdExistingCourse() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");

        // Act
        courseRepository.insert(course);
        Course result = courseRepository.findById("1");

        // Assert
        Assertions.assertEquals(course, result);
    }

    @Test
    public void testFindByIdNonExistingCourse() {
        // Act & Assert
        Assertions.assertNull(courseRepository.findById("1"));
    }

    @Test
    public void testFindAll() {
        // Arrange
        Course course1 = new Course();
        course1.setId("1");
        course1.setName("Sistemas de Informação");
        Course course2 = new Course();
        course2.setId("2");
        course2.setName("Engenharia da Computação");

        // Act
        courseRepository.insert(course1);
        courseRepository.insert(course2);
        Collection<Course> result = courseRepository.findAll();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(course1));
        Assertions.assertTrue(result.contains(course2));
    }

    @Test
    public void testInsert() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");

        // Act
        Course result = courseRepository.insert(course);

        // Assert
        Assertions.assertEquals(course, result);
        Assertions.assertTrue(courseRepository.findById("1") != null);
    }

    @Test
    public void testInsertExistingCourse() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");
        courseRepository.insert(course);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            courseRepository.insert(course);
        });
    }

    @Test
    public void testUpdateExistingCourse() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");
        courseRepository.insert(course);

        // Act
        Course updatedCourse = new Course();
        updatedCourse.setId("1");
        updatedCourse.setName("Engenharia da Computação");
        Course result = courseRepository.update("1", updatedCourse);

        // Assert
        Assertions.assertEquals(updatedCourse, result);
        Assertions.assertEquals("Engenharia da Computação", courseRepository.findById("1").getName());
    }

    @Test
    public void testUpdateNonExistingCourse() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            courseRepository.update("1", course);
        });
    }

    @Test
    public void testDeleteExistingCourse() {
        // Arrange
        Course course = new Course();
        course.setId("1");
        course.setName("Sistemas de Informação");
        courseRepository.insert(course);

        // Act
        courseRepository.delete("1");

        // Assert
        Assertions.assertNull(courseRepository.findById("1"));
    }

    @Test
    public void testDeleteNonExistingCourse() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            courseRepository.delete("1");
        });
    }

}

