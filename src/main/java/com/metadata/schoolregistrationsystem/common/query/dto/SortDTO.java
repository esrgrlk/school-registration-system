package com.metadata.schoolregistrationsystem.common.query.dto;

import com.metadata.schoolregistrationsystem.common.query.enums.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortDTO {
    private String field;

    private SortDirection direction;

    public SortDTO() {
        this.direction = SortDirection.ASC;
    }
}
