package com.metadata.schoolregistrationsystem.course.controller;

import com.metadata.schoolregistrationsystem.common.query.QuerySpecification;
import com.metadata.schoolregistrationsystem.common.query.dto.CriteriaDTO;
import com.metadata.schoolregistrationsystem.course.dto.CourseDTO;
import com.metadata.schoolregistrationsystem.course.dto.CourseStudentDTO;
import com.metadata.schoolregistrationsystem.course.dto.CreateCourseDTO;
import com.metadata.schoolregistrationsystem.course.dto.UpdateCourseDTO;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.course.mapper.CourseMapper;
import com.metadata.schoolregistrationsystem.course.mapper.CourseStudentMapper;
import com.metadata.schoolregistrationsystem.course.mapper.CreateCourseMapper;
import com.metadata.schoolregistrationsystem.course.mapper.UpdateCourseMapper;
import com.metadata.schoolregistrationsystem.course.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses().stream().map(CourseMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return CourseMapper.INSTANCE.entityToDto(courseService.getCourseById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        courseService.create(CreateCourseMapper.INSTANCE.dtoToEntity(createCourseDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        courseService.update(UpdateCourseMapper.INSTANCE.dtoToEntity(updateCourseDTO));
    }

    @PostMapping("/query")
    public Page<CourseStudentDTO> query(@RequestBody CriteriaDTO criteriaDTO) {
        QuerySpecification<Course> specification = new QuerySpecification<>(criteriaDTO);
        Pageable pageable = QuerySpecification.getPageable(criteriaDTO.getPage(), criteriaDTO.getSize());
        return courseService.query(specification, pageable).map(CourseStudentMapper.INSTANCE::entityToDto);
    }

    @GetMapping("/withoutStudents")
    public List<CourseStudentDTO> getCoursesWithoutStudents() {
        return courseService.getCoursesWithoutStudents().stream().map(CourseStudentMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }
}
