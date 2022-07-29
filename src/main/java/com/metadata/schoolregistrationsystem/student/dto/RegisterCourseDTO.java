package com.metadata.schoolregistrationsystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourseDTO {

    @NotNull
    private Long id;

    @NotEmpty
    private List<Long> courseIdList;
}
