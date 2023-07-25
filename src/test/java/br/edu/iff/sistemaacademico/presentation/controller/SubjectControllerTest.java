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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SubjectController.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SubjectControllerTest {

    @MockBean
    private GetSubjectUseCase getSubjectUseCase;
    @MockBean
    private GetSubjectsUseCase getSubjectsUseCase;
    @MockBean
    private CreateSubjectUseCase createSubjectUseCase;
    @MockBean
    private DeleteSubjectUseCase deleteSubjectUseCase;
    @MockBean
    private UpdateSubjectUseCase updateSubjectUseCase;
    @MockBean
    private AddSubjectStudentUseCase addSubjectStudentUseCase;
    @MockBean
    private RemoveSubjectStudentUseCase removeSubjectStudentUseCase;

    @Autowired
    private SubjectController subjectController;

    @Test
    public void testGetAllSubjects() {
        Collection<Subject> mockSubjects = new ArrayList<>();
        mockSubjects.add(new Subject());

        when(getSubjectsUseCase.perform()).thenReturn(mockSubjects);

        ResponseEntity response = subjectController.getAllSubjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubjects, response.getBody());
    }

    @Test
    public void testGetSubjectExisting() {
        String subjectId = "subjectid";
        Subject mockSubject = new Subject();
        mockSubject.setId(subjectId);

        when(getSubjectUseCase.perform(subjectId)).thenReturn(mockSubject);

        ResponseEntity response = subjectController.getSubject(subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubject, response.getBody());
    }

    @Test
    public void testGetSubjectNonExisting() {
        String subjectId = "subjectid";
        when(getSubjectUseCase.perform(subjectId)).thenReturn(null);

        ResponseEntity response = subjectController.getSubject(subjectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testPostSubjectValidData() {
        RequestSubject requestData = new RequestSubject("Topicos II", 80, "BSI", "professor");

        ResponseEntity response = subjectController.postSubject(requestData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(createSubjectUseCase, times(1)).perform(requestData);
    }

    @Test
    public void testPostSubjectInvalidData() {
        RequestSubject requestData = new RequestSubject(null, null, null, null);

        when(createSubjectUseCase.perform(any())).thenThrow(IllegalArgumentException.class);

        ResponseEntity response = subjectController.postSubject(requestData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(createSubjectUseCase, times(1)).perform(requestData);
    }

    @Test
    public void testPutSubjectExisting() {
        RequestSubject requestData = new RequestSubject("Topicos II", 80, "BSI", "professor");
        String subjectId = "subjectid";

        ResponseEntity response = subjectController.putSubject(subjectId, requestData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateSubjectUseCase, times(1)).perform(subjectId, requestData);
    }

    @Test
    public void testPutSubjectNonExistingOrInvalid() {
        RequestSubject requestData = new RequestSubject("Topicos II", 80, "BSI", "professor");
        String subjectId = "subjectid";

        when(updateSubjectUseCase.perform(any(), any())).thenThrow(IllegalArgumentException.class);

        ResponseEntity response = subjectController.putSubject(subjectId, requestData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(updateSubjectUseCase, times(1)).perform(subjectId, requestData);
    }

    @Test
    public void testDeleteSubjectExisting() {
        String subjectId = "subjectid";
        String professorId = "professorId";

        ResponseEntity response = subjectController.deleteSubject(subjectId, professorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(deleteSubjectUseCase, times(1)).perform(subjectId, professorId);
    }

    @Test
    public void testDeleteSubjectNonExisting() {
        String subjectId = "subjectid";
        String professorId = "professorId";

        doThrow(IllegalArgumentException.class).when(deleteSubjectUseCase).perform(subjectId, professorId);

        ResponseEntity response = subjectController.deleteSubject(subjectId, professorId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(deleteSubjectUseCase, times(1)).perform(subjectId, professorId);
    }

    @Test
    public void testAddStudentExisting(){
        String subjectId = "subjectid";
        String studentId = "studentId";

        ResponseEntity response = subjectController.addStudent(subjectId, studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(addSubjectStudentUseCase, times(1)).perform(subjectId, studentId);
    }

    @Test
    public void testAddStudentNonExisting(){
        String subjectId = "subjectid";
        String studentId = "studentId";

        doThrow(IllegalArgumentException.class).when(addSubjectStudentUseCase).perform(studentId, subjectId);

        ResponseEntity response = subjectController.addStudent(studentId, subjectId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(addSubjectStudentUseCase, times(1)).perform(studentId, subjectId);
    }

    @Test
    public void testRemoveStudentExisting(){
        String subjectId = "subjectid";
        String studentId = "studentId";

        ResponseEntity response = subjectController.removeStudent(studentId, subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(removeSubjectStudentUseCase, times(1)).perform(studentId, subjectId);
    }

    @Test
    public void testRemoveStudentNonExisting(){
        String subjectId = "subjectid";
        String studentId = "studentId";

        doThrow(IllegalArgumentException.class).when(removeSubjectStudentUseCase).perform(studentId, subjectId);

        ResponseEntity response = subjectController.removeStudent(studentId, subjectId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(removeSubjectStudentUseCase, times(1)).perform(studentId, subjectId);
    }
}
