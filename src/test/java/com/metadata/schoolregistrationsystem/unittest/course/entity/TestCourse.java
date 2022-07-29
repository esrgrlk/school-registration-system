package com.metadata.schoolregistrationsystem.unittest.course.entity;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {

    private Course course;

    @BeforeEach
    public void beforeTest() {
        course = new Course();
        course.setName("Data Structures");
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(new Student());
        course.setStudents(studentSet);
    }

    @Test
    public void testUpdate() {
        Course updatedCourse = new Course();
        updatedCourse.setName("Algorithms");

        course.update(updatedCourse);

        assertEquals(updatedCourse.getName(), course.getName());
    }

    @Test
    public void testHasEnoughStudentCapacity() {
        course.getStudents().add(new Student());
        course.getStudents().add(new Student());

        assertTrue(course.hasEnoughStudentCapacity());
    }

    @Test
    public void testHasNotEnoughStudentCapacity() {
        for (int i = 1; i < 50; i++) {
            course.getStudents().add(new Student());
        }

        assertFalse(course.hasEnoughStudentCapacity());
    }
}
