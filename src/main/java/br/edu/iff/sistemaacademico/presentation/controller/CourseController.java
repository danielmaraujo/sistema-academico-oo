package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.usecase.GetCourseUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetCoursesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class CourseController {
    final GetCourseUseCase getCourseUseCase;
    final GetCoursesUseCase getCoursesUseCase;

    public CourseController(GetCourseUseCase getCourseUseCase, GetCoursesUseCase getCoursesUseCase) {
        this.getCourseUseCase = getCourseUseCase;
        this.getCoursesUseCase = getCoursesUseCase;
    }

    @GetMapping(value = "/courses")
    public ResponseEntity getAllCourses(){
        Collection<Course> subjects = getCoursesUseCase.perform();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{id}")
    public ResponseEntity getCourse(@PathVariable("id") String courseId){
        Course course = getCourseUseCase.perform(courseId);

        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
