package br.edu.iff.sistemaacademico.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Table(name = "professor")
@Entity(name = "professor")
@Data
@NoArgsConstructor
public class Professor extends User{
}
