package com.metadata.schoolregistrationsystem.course.mapper;

import com.metadata.schoolregistrationsystem.course.dto.CreateCourseDTO;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateCourseMapper {

    CreateCourseMapper INSTANCE = Mappers.getMapper(CreateCourseMapper.class);

    Course dtoToEntity(CreateCourseDTO createCourseDTO);
}
