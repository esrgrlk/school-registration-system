package com.metadata.schoolregistrationsystem.course.mapper;

import com.metadata.schoolregistrationsystem.course.dto.UpdateCourseDTO;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateCourseMapper {

    UpdateCourseMapper INSTANCE = Mappers.getMapper(UpdateCourseMapper.class);

    Course dtoToEntity(UpdateCourseDTO updateCourseDTO);
}
