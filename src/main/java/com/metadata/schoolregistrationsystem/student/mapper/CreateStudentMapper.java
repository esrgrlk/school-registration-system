package com.metadata.schoolregistrationsystem.student.mapper;

import com.metadata.schoolregistrationsystem.student.dto.CreateStudentDTO;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateStudentMapper {

    CreateStudentMapper INSTANCE = Mappers.getMapper(CreateStudentMapper.class);

    Student dtoToEntity(CreateStudentDTO createStudentDTO);
}
