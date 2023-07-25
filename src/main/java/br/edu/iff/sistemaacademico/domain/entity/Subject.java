package br.edu.iff.sistemaacademico.domain.entity;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "subject")
@Entity(name = "subject")
@Data
public class Subject extends br.edu.iff.sistemaacademico.domain.entity.Entity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "course_load")
    private Integer courseLoad;

    @Column(name = "professor")
    private String professor;

    @Column(name = "course")
    private String course;

    @ElementCollection
    private List<String> students;

    public Subject() {
        students = new ArrayList<>();
    }

    public Subject(RequestSubject requestSubject){
        name = requestSubject.name();
        courseLoad = requestSubject.courseLoad();
        course = requestSubject.courseId();
        professor = requestSubject.professorId();
        students = new ArrayList<>();
    }
}
