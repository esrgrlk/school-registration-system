package com.metadata.schoolregistrationsystem.common.query.enums;

import com.metadata.schoolregistrationsystem.common.query.dto.SortDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public enum SortDirection {
    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortDTO sortDTO) {
            return cb.asc(root.get(sortDTO.getField()));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortDTO sortDTO) {
            return cb.desc(root.get(sortDTO.getField()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortDTO sortDTO);
}