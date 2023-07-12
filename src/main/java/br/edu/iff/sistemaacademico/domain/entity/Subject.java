package br.edu.iff.sistemaacademico.domain.entity;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "subject")
@Entity(name = "subject")
@Data
@NoArgsConstructor
public class Subject extends br.edu.iff.sistemaacademico.domain.entity.Entity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "course_load")
    private Integer courseLoad;

    @Column(name = "professor")
    @ManyToOne
    private Professor professor;

    @Column(name = "course")
    @ManyToOne
    private Course course;

    @Column(name = "students")
    @OneToMany(mappedBy = "subject")
    private List<Student> students;

    public Subject(RequestSubject requestSubject){
        name = requestSubject.name();
        courseLoad = requestSubject.course_load();
    }
}
