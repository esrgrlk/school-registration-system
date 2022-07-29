package com.metadata.schoolregistrationsystem.integrationtest.student;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.repository.CourseRepository;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import com.metadata.schoolregistrationsystem.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TestStudentRepository {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testFindByCourses() {
        Optional<Course> course = courseRepository.findById(2L);
        List<Student> studentList = studentRepository.findByCourses(course.get());
        assertEquals(studentList.size(), 2);
    }
}
