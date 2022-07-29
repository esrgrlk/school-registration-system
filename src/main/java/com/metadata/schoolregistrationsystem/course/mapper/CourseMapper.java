package com.metadata.schoolregistrationsystem.course.mapper;

import com.metadata.schoolregistrationsystem.course.dto.CourseDTO;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO entityToDto(Course course);

    Course dtoToEntity(CourseDTO courseDTO);
}
