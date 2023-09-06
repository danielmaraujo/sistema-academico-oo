package br.edu.iff.sistemaacademico.presentation.controller;

import br.edu.iff.sistemaacademico.domain.dto.RequestSubject;
import br.edu.iff.sistemaacademico.domain.entity.Subject;
import br.edu.iff.sistemaacademico.domain.usecase.AddSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.CreateSubjectUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.DeleteSubjectUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.GetSubjectsUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.RemoveSubjectStudentUseCase;
import br.edu.iff.sistemaacademico.domain.usecase.UpdateSubjectUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class SubjectController {
    final GetSubjectUseCase getSubjectUseCase;
    final GetSubjectsUseCase getSubjectsUseCase;
    final CreateSubjectUseCase createSubjectUseCase;
    final DeleteSubjectUseCase deleteSubjectUseCase;
    final UpdateSubjectUseCase updateSubjectUseCase;
    final AddSubjectStudentUseCase addSubjectStudentUseCase;
    final RemoveSubjectStudentUseCase removeSubjectStudentUseCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public SubjectController(GetSubjectUseCase getSubjectUseCase, GetSubjectsUseCase getSubjectsUseCase, CreateSubjectUseCase createSubjectUseCase, DeleteSubjectUseCase deleteSubjectUseCase, UpdateSubjectUseCase updateSubjectUseCase, AddSubjectStudentUseCase addSubjectStudentUseCase, RemoveSubjectStudentUseCase removeSubjectStudentUseCase) {
        this.getSubjectUseCase = getSubjectUseCase;
        this.getSubjectsUseCase = getSubjectsUseCase;
        this.createSubjectUseCase = createSubjectUseCase;
        this.deleteSubjectUseCase = deleteSubjectUseCase;
        this.updateSubjectUseCase = updateSubjectUseCase;
        this.addSubjectStudentUseCase = addSubjectStudentUseCase;
        this.removeSubjectStudentUseCase = removeSubjectStudentUseCase;
    }

    @GetMapping(value = "/subjects")
    public ResponseEntity getAllSubjects(){
        LOGGER.info("GET /subjects");

        Collection<Subject> subjects = getSubjectsUseCase.perform();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(value = "/subjects/{id}")
    public ResponseEntity getSubject(@PathVariable("id") String subjectId){
        LOGGER.info("GET /subjects/{}", subjectId);

        Subject subject = getSubjectUseCase.perform(subjectId);

        if (subject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping(value = "/subjects")
    public ResponseEntity postSubject(@RequestBody @Valid RequestSubject data){
        LOGGER.info("POST /subjects");

        try{
            createSubjectUseCase.perform(data);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/subjects/{id}")
    public ResponseEntity putSubject(@PathVariable("id") String subjectId, @RequestBody @Valid RequestSubject data){
        LOGGER.info("PUT /subjects/{}", subjectId);

        try{
            updateSubjectUseCase.perform(subjectId, data);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/subjects/{id}")
    public ResponseEntity deleteSubject(@PathVariable("id") String subjectId, @RequestHeader("professorId") String professorId){
        LOGGER.info("DELETE /subjects/{}", subjectId);

        try{
            deleteSubjectUseCase.perform(subjectId, professorId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/subjects/{subjectId}/student/{studentId}")
    public ResponseEntity addStudent(@PathVariable("studentId") String studentId, @PathVariable("subjectId") String subjectId){
        LOGGER.info("PUT /subjects/{}/student/{}", subjectId, studentId);

        try{
            addSubjectStudentUseCase.perform(studentId, subjectId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/subjects/{subjectId}/student/{studentId}")
    public ResponseEntity removeStudent (@PathVariable("studentId") String studentId, @PathVariable("subjectId") String subjectId){
        LOGGER.info("DELETE /subjects/{}/student/{}", subjectId, studentId);

        try{
            removeSubjectStudentUseCase.perform(studentId, subjectId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
