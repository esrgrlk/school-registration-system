package com.metadata.schoolregistrationsystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDTO {

    @Size(max = 255)
    @NotBlank
    private String name;

    @Size(max = 255)
    @NotBlank
    private String surname;
}
