package com.metadata.schoolregistrationsystem.course.dto;

import com.metadata.schoolregistrationsystem.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentDTO {

    private Long id;
    private String name;
    private List<StudentDTO> studentDTOList;
}
