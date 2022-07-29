package com.metadata.schoolregistrationsystem.integrationtest.course;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.repository.CourseRepository;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import com.metadata.schoolregistrationsystem.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace= NONE)
public class TestCourseRepository {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindByStudents() {
        Optional<Student> student = studentRepository.findById(1001L);
        List<Course> courseList = courseRepository.findByStudents(student.get());
        assertEquals(courseList.size(), 3);
    }

    @Test
    public void testCreate() {
        Course course = new Course();
        course.setName("Geometry");

        courseRepository.save(course);

        assertNotNull(course.getId());
        assertNotNull(course.getCreatedDate());
    }
}
