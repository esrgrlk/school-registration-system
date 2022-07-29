package com.metadata.schoolregistrationsystem.student.mapper;

import com.metadata.schoolregistrationsystem.course.mapper.CourseMapper;
import com.metadata.schoolregistrationsystem.student.dto.StudentCourseDTO;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CourseMapper.class})
public interface StudentCourseMapper {

    StudentCourseMapper INSTANCE = Mappers.getMapper(StudentCourseMapper.class);

    @Mappings({
            @Mapping(target = "courseDTOList", source = "courses")
    })
    StudentCourseDTO entityToDto(Student student);
}
