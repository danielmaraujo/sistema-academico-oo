package br.edu.iff.sistemaacademico.repository;

import br.edu.iff.sistemaacademico.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
