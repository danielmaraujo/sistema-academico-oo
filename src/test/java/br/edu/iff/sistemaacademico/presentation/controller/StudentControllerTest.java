package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentsUseCase;
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

@SpringBootTest(classes = StudentController.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class StudentControllerTest {

    @MockBean
    private GetStudentUseCase getStudentUseCase;
    @MockBean
    private GetStudentsUseCase getStudentsUseCase;

    @Autowired
    private StudentController studentController;

    @Test
    public void testGetAllStudents() {
        Collection<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student());

        when(getStudentsUseCase.perform()).thenReturn(mockStudents);

        ResponseEntity responseEntity = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockStudents, responseEntity.getBody());
    }

    @Test
    public void testGetStudent() {
        Student mockStudent = new Student();
        mockStudent.setId("1");

        when(getStudentUseCase.perform("1")).thenReturn(mockStudent);

        ResponseEntity responseEntity = studentController.getStudent("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockStudent, responseEntity.getBody());
    }
}
