package com.metadata.schoolregistrationsystem.unittest.student.entity;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    private Student student;

    @BeforeEach
    public void beforeTest() {
        student = new Student();
        student.setName("Harry");
        student.setSurname("Potter");
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(new Course());
        student.setCourses(courseSet);
    }

    @Test
    public void testUpdate() {
        Student updatedStudent = new Student();
        updatedStudent.setName("Ron");
        updatedStudent.setSurname("Weasley");

        student.update(updatedStudent);

        assertEquals(updatedStudent.getName(), student.getName());
        assertEquals(updatedStudent.getSurname(), student.getSurname());
    }

    @Test
    public void testHasEnoughCourseCapacity() {
        student.getCourses().add(new Course());

        assertTrue(student.hasEnoughCourseCapacity(2));
    }

    @Test
    public void testHasNotEnoughCourseCapacity() {
        student.getCourses().add(new Course());

        assertFalse(student.hasEnoughCourseCapacity(4));
    }

    @Test
    public void testAddNewCourses() {
        List<Course> courseList = Arrays.asList(new Course(), new Course());
        student.addNewCourses(courseList);

        assertEquals(3, student.getCourses().size());
    }
}
