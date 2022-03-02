package com.epam.esm.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface CriteriaSpecification<T> {

    Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
