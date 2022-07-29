package com.metadata.schoolregistrationsystem.student.dto;

import com.metadata.schoolregistrationsystem.course.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDTO {

    private Long id;
    private String name;
    private String surname;
    private List<CourseDTO> courseDTOList;
}
