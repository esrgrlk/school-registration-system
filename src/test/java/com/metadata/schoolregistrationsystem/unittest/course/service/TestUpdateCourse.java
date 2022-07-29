package com.metadata.schoolregistrationsystem.unittest.course.service;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.repository.CourseRepository;
import com.metadata.schoolregistrationsystem.course.service.CourseMessages;
import com.metadata.schoolregistrationsystem.course.service.CourseService;
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
public class TestUpdateCourse {

    private static final Long ID = 1000L;

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    private Course course;

    @BeforeEach
    void beforeEach() {
        course = new Course();
        course.setId(ID);
        course.setName("Algorithms");
    }

    @Test
    public void testCourseNotFound() {
        Course updatedCourse = new Course();
        updatedCourse.setId(ID);
        updatedCourse.setName("History");

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> courseService.update(updatedCourse));

        assertEquals(CourseMessages.ERROR_COURSE_NOT_FOUND, thrown.getReason());
    }

    @Test
    public void testCourseFound() {
        Course updatedCourse = new Course();
        updatedCourse.setId(ID);
        updatedCourse.setName("History");

        when(courseRepository.findById(ID)).thenReturn(Optional.of(course));

        courseService.update(updatedCourse);

        assertEquals(updatedCourse.getName(), course.getName());
        verify(courseRepository).findById(ID);
    }
}
