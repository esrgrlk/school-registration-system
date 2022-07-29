package com.metadata.schoolregistrationsystem.common.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDTO {

    private List<FilterDTO> filters;

    private List<SortDTO> sortFields;

    private Integer page;

    private Integer size;
}
