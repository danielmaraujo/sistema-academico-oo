package br.edu.iff.sistemaacademico;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Professor;
import br.edu.iff.sistemaacademico.domain.entity.Student;
import br.edu.iff.sistemaacademico.repository.CourseRepository;
import br.edu.iff.sistemaacademico.repository.ProfessorRepository;
import br.edu.iff.sistemaacademico.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    final StudentRepository studentRepository;
    final CourseRepository courseRepository;
    final ProfessorRepository professorRepository;

    public DataInitializer(StudentRepository studentRepository, CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @PostConstruct
    public void initializeData(){
        Course c1 = new Course();
        c1.setId("BSI");
        c1.setName("Sistemas de Informação");

        Course c2 = new Course();
        c2.setId("ENGCOMP");
        c2.setName("Engenharia da Computação");

        courseRepository.insert(c1);
        courseRepository.insert(c2);

        Student s1 = new Student();
        s1.setId("123");
        s1.setName("Daniel");
        s1.setStartYear(2019);
        s1.setCourse("BSI");

        Student s2 = new Student();
        s2.setId("321");
        s2.setName("João");
        s2.setStartYear(2022);
        s2.setCourse("ENGCOMP");

        studentRepository.insert(s1);
        studentRepository.insert(s2);

        Professor p1 = new Professor();
        p1.setUsername("fernando");
        p1.setPassword("123");
        p1.setName("Fernando");

        professorRepository.insert(p1);
    }
}
