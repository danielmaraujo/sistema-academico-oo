package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.usecase.GetCourseUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetCoursesUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseController.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CourseControllerTest {

    @MockBean
    private GetCourseUseCase getCourseUseCase;
    @MockBean
    private GetCoursesUseCase getCoursesUseCase;

    @Autowired
    private CourseController courseController;

    @Test
    public void testGetAllCourses() {
        Collection<Course> mockCourses = new ArrayList<>();
        mockCourses.add(new Course());

        when(getCoursesUseCase.perform()).thenReturn(mockCourses);

        ResponseEntity responseEntity = courseController.getAllCourses();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCourses, responseEntity.getBody());
    }

    @Test
    public void testGetCourse() {
        Course mockCourse = new Course();
        mockCourse.setId("1");

        when(getCourseUseCase.perform("1")).thenReturn(mockCourse);

        ResponseEntity responseEntity = courseController.getCourse("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCourse, responseEntity.getBody());
    }
}
