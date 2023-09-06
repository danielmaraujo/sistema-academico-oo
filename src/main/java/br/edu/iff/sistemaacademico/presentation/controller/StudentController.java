package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetStudentsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class StudentController {
    final GetStudentUseCase getStudentUseCase;
    final GetStudentsUseCase getStudentsUseCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public StudentController(GetStudentUseCase getStudentUseCase, GetStudentsUseCase getStudentsUseCase) {
        this.getStudentUseCase = getStudentUseCase;
        this.getStudentsUseCase = getStudentsUseCase;
    }

    @GetMapping(value = "/students")
    public ResponseEntity getAllStudents(){
        LOGGER.info("GET /students");

        Collection<Student> subjects = getStudentsUseCase.perform();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity getStudent(@PathVariable("id") String studentId){
        LOGGER.info("GET /students/{}", studentId);

        Student student = getStudentUseCase.perform(studentId);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
