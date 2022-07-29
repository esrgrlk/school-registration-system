package com.metadata.schoolregistrationsystem.course.service;

import com.metadata.schoolregistrationsystem.common.query.QuerySpecification;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCoursesById(List<Long> idList) {
        return courseRepository.findAllById(idList);
    }

    @Transactional
    public void create(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void delete(Long id) {
        Course course = retriveByIdOrElseThrow(id);
        courseRepository.delete(course);
    }

    @Transactional
    public void update(Course course) {
        Course existingCourse = retriveByIdOrElseThrow(course.getId());
        existingCourse.update(course);
    }

    @Transactional(readOnly = true)
    public Page<Course> query(QuerySpecification<Course> specification, Pageable pageable) {
        return courseRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public List<Course> getCoursesWithoutStudents() {
        return courseRepository.findByStudents(null);
    }

    private Course retriveByIdOrElseThrow(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, CourseMessages.ERROR_COURSE_NOT_FOUND);
        }
        return course;
    }
}
