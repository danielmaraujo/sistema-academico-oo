package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.usecase.GetCourseUseCase;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
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

@SpringBootTest(classes = GetCourseUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GetCourseUseCaseImplTest {

    @MockBean
    private CourseRepository repository;

    @Autowired
    private GetCourseUseCase getCourseUseCase;

    @Test
    public void testPerform() {
        // Given
        String courseId = "123";
        Course course = new Course();
        course.setId(courseId);

        when(repository.findById(courseId)).thenReturn(course);
        // When
        Course result = getCourseUseCase.perform(courseId);

        // Then
        assertEquals(course, result);
        verify(repository, times(1)).findById(courseId);
    }
}
