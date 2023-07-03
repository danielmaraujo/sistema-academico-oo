package br.edu.iff.sistemaacademico.controllers;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.usecase.CreateSubjectUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SubjectController {

    @Autowired
    private CreateSubjectUseCase createSubjectUseCase;

    @GetMapping(
        value = "/course/{courseId}/subject"
    )
    public ResponseEntity getAllSubjects(@PathVariable("courseId") String courseId){

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/subject")
    public ResponseEntity postSubject(@RequestBody @Valid RequestSubject data){
        createSubjectUseCase.perform(data);
        return ResponseEntity.ok().build();
    }
}
