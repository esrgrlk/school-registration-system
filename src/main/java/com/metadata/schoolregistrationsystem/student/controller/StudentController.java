package com.metadata.schoolregistrationsystem.student.controller;

import com.metadata.schoolregistrationsystem.common.query.QuerySpecification;
import com.metadata.schoolregistrationsystem.common.query.dto.CriteriaDTO;
import com.metadata.schoolregistrationsystem.student.dto.*;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import com.metadata.schoolregistrationsystem.student.mapper.CreateStudentMapper;
import com.metadata.schoolregistrationsystem.student.mapper.StudentCourseMapper;
import com.metadata.schoolregistrationsystem.student.mapper.StudentMapper;
import com.metadata.schoolregistrationsystem.student.mapper.UpdateStudentMapper;
import com.metadata.schoolregistrationsystem.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents().stream().map(StudentMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return StudentMapper.INSTANCE.entityToDto(studentService.getStudentById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody CreateStudentDTO createStudentDTO) {
        studentService.create(CreateStudentMapper.INSTANCE.dtoToEntity(createStudentDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody UpdateStudentDTO updateStudentDTO) {
        studentService.update(UpdateStudentMapper.INSTANCE.dtoToEntity(updateStudentDTO));
    }

    @PostMapping("/registerToCourses")
    public StudentCourseDTO registerToCourses(@Valid @RequestBody RegisterCourseDTO registerCourseDTO) {
        Student student = studentService.registerToCourses(registerCourseDTO.getId(), registerCourseDTO.getCourseIdList());
        return StudentCourseMapper.INSTANCE.entityToDto(student);
    }

    @PostMapping("/query")
    public Page<StudentCourseDTO> query(@RequestBody CriteriaDTO criteriaDTO) {
        QuerySpecification<Student> specification = new QuerySpecification<>(criteriaDTO);
        Pageable pageable = QuerySpecification.getPageable(criteriaDTO.getPage(), criteriaDTO.getSize());
        return studentService.query(specification, pageable).map(StudentCourseMapper.INSTANCE::entityToDto);
    }

    @GetMapping("/withoutCourses")
    public List<StudentCourseDTO> getStudentsWithoutCourses() {
        return studentService.getStudentsWithoutCourses().stream().map(StudentCourseMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }
}