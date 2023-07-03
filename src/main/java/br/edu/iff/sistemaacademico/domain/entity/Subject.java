package br.edu.iff.sistemaacademico.domain.entity;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "subject")
@Entity(name = "subject")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "course_load")
    private int courseLoad;

    public Subject(RequestSubject requestSubject){
        name = requestSubject.name();
        courseLoad = requestSubject.course_load();
    }
}
