package com.metadata.schoolregistrationsystem.student.mapper;

import com.metadata.schoolregistrationsystem.student.dto.StudentDTO;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO entityToDto(Student student);

    Student dtoToEntity(StudentDTO studentDTO);
}
