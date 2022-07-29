package com.metadata.schoolregistrationsystem.common.query.dto;

import com.metadata.schoolregistrationsystem.common.query.enums.FieldType;
import com.metadata.schoolregistrationsystem.common.query.enums.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private String field;

    private QueryOperator operator;

    private FieldType fieldType;

    private String value;

    private List<String> values;
}
