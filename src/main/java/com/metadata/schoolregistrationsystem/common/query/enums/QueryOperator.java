package com.metadata.schoolregistrationsystem.common.query.enums;

import com.metadata.schoolregistrationsystem.common.query.dto.FilterDTO;

import javax.persistence.criteria.*;

public enum QueryOperator {
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDTO filter, Predicate predicate) {
            Object value = filter.getFieldType().parse(filter.getValue());
            String[] fields = filter.getField().split("\\.");
            if (fields.length > 1) {
                Join<?, ?> join = root.join(fields[0], JoinType.INNER);
                return cb.equal(join.get(fields[1]), value);
            }
            Expression<?> key = root.get(filter.getField());
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDTO filter, Predicate predicate) {
            Object value = filter.getFieldType().parse(filter.getValue());
            Expression<?> key = root.get(filter.getField());
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDTO filter, Predicate predicate) {
            Expression<String> key = root.get(filter.getField());
            return cb.and(cb.like(cb.upper(key), "%" + filter.getValue().toUpperCase() + "%"), predicate);
        }
    },

    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDTO filter, Predicate predicate) {
            CriteriaBuilder.In<Object> inClause = cb.in(root.get(filter.getField()));
            for (Object value : filter.getValues()) {
                inClause.value(filter.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDTO filter, Predicate predicate);
}
