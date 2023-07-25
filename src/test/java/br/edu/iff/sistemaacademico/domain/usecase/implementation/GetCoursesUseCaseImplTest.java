package br.edu.iff.sistemaacademico.domain.usecase.implementation;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.usecase.GetCoursesUseCase;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
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

@SpringBootTest(classes = GetCoursesUseCaseImpl.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GetCoursesUseCaseImplTest {

    @MockBean
    private CourseRepository repository;

    @Autowired
    private GetCoursesUseCase getCoursesUseCase;

    @Test
    public void testPerform() {
        // Given
        Collection<Course> courses = new ArrayList<>();
        Course course = new Course();
        courses.add(course);

        when(repository.findAll()).thenReturn(courses);

        // When
        Collection<Course> result = getCoursesUseCase.perform();

        // Then
        assertEquals(courses, result);
        verify(repository, times(1)).findAll();
    }
}
