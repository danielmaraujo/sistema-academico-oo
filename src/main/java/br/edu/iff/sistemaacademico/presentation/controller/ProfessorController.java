package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.entity.Course;
import br.edu.iff.sistemaacademico.domain.entity.Professor;
import br.edu.iff.sistemaacademico.domain.usecase.GetProfessorsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProfessorController {
    final GetProfessorsUseCase getProfessorsUseCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorController.class);

    public ProfessorController(GetProfessorsUseCase getProfessorsUseCase) {
        this.getProfessorsUseCase = getProfessorsUseCase;
    }


    @GetMapping(value = "/professors")
    public ResponseEntity getAllProfessors() {
        LOGGER.info("GET /professors");

        Collection<Professor> subjects = getProfessorsUseCase.perform();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
