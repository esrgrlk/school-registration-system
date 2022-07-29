package com.metadata.schoolregistrationsystem.common.query;

import com.metadata.schoolregistrationsystem.common.query.dto.CriteriaDTO;
import com.metadata.schoolregistrationsystem.common.query.dto.FilterDTO;
import com.metadata.schoolregistrationsystem.common.query.dto.SortDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class QuerySpecification<T> implements Specification<T> {

    private static final int DEFAULT_PAGE_SIZE = 50;

    private final CriteriaDTO criteriaDTO;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterDTO filter : criteriaDTO.getFilters()) {
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortDTO sortField : criteriaDTO.getSortFields()) {
            orders.add(sortField.getDirection().build(root, cb, sortField));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, DEFAULT_PAGE_SIZE));
    }
}