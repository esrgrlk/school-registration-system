package com.metadata.schoolregistrationsystem.student.repository;

import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    List<Student> findByCourses(Course course);
}
