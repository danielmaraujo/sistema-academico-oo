package br.edu.iff.sistemaacademico.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Table(name = "student")
@Entity(name = "student")
@Data
@NoArgsConstructor
public class Student extends br.edu.iff.sistemaacademico.domain.entity.Entity {
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "startYear")
    private Integer startYear;

    @NotNull
    @Column(name = "course")
    private String course;
}
