package com.metadata.schoolregistrationsystem.course.mapper;

import com.metadata.schoolregistrationsystem.course.dto.CourseStudentDTO;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import com.metadata.schoolregistrationsystem.student.mapper.StudentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {StudentMapper.class})
public interface CourseStudentMapper {

    CourseStudentMapper INSTANCE = Mappers.getMapper(CourseStudentMapper.class);

    @Mappings({
            @Mapping(target = "studentDTOList", source = "students")
    })
    CourseStudentDTO entityToDto(Course course);
}
