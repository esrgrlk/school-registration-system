package com.metadata.schoolregistrationsystem.unittest.student.service;

import com.metadata.schoolregistrationsystem.student.entity.Student;
import com.metadata.schoolregistrationsystem.student.repository.StudentRepository;
import com.metadata.schoolregistrationsystem.student.service.StudentMessages;
import com.metadata.schoolregistrationsystem.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUpdateStudent {

    private static final Long ID = 1000L;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void beforeEach() {
        student = new Student();
        student.setId(ID);
        student.setName("John");
        student.setSurname("White");
    }

    @Test
    public void testStudentNotFound() {
        Student updatedStudent = new Student();
        updatedStudent.setId(ID);
        updatedStudent.setName("Harry");

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> studentService.update(updatedStudent));

        assertEquals(StudentMessages.ERROR_STUDENT_NOT_FOUND, thrown.getReason());
    }

    @Test
    public void testStudentFound() {
        Student updatedStudent = new Student();
        updatedStudent.setId(ID);
        updatedStudent.setName("Harry");

        when(studentRepository.findById(ID)).thenReturn(Optional.of(student));

        studentService.update(updatedStudent);

        assertEquals(updatedStudent.getName(), student.getName());
        verify(studentRepository).findById(ID);
    }
}