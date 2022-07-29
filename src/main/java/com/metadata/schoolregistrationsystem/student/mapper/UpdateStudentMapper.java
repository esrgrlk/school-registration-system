package com.metadata.schoolregistrationsystem.student.mapper;

import com.metadata.schoolregistrationsystem.student.dto.UpdateStudentDTO;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateStudentMapper {

    UpdateStudentMapper INSTANCE = Mappers.getMapper(UpdateStudentMapper.class);

    Student dtoToEntity(UpdateStudentDTO updateStudentDTO);
}
