package com.metadata.schoolregistrationsystem.student.service;

import com.metadata.schoolregistrationsystem.common.query.QuerySpecification;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.service.CourseService;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import com.metadata.schoolregistrationsystem.student.repository.StudentRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void create(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = retriveByIdOrElseThrow(id);
        studentRepository.delete(student);
    }

    @Transactional
    public void update(Student student) {
        Student existingStudent = retriveByIdOrElseThrow(student.getId());
        existingStudent.update(student);
    }

    @Transactional
    public Student registerToCourses(Long id, List<Long> courseIdList) {
        Student student = retriveByIdOrElseThrow(id);

        List<Course> courses = courseService.getAllCoursesById(courseIdList);

        if (!student.hasEnoughCourseCapacity(courses.size())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, StudentMessages.ERROR_STUDENT_MAX_COURSE_CAPACITY);
        }

        for (Course course : courses) {
            if (!course.hasEnoughStudentCapacity()) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, StudentMessages.ERROR_COURSE_MAX_STUDENT_CAPACITY);
            }
        }
        student.addNewCourses(courses);
        return student;
    }

    @Transactional(readOnly = true)
    public Page<Student> query(QuerySpecification<Student> specification, Pageable pageable) {
        return studentRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsWithoutCourses() {
        return studentRepository.findByCourses(null);
    }

    private Student retriveByIdOrElseThrow(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, StudentMessages.ERROR_STUDENT_NOT_FOUND);
        }
        return student;
    }
}
