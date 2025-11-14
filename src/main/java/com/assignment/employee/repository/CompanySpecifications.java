package com.assignment.employee.repository;

import com.assignment.employee.entity.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecifications {
    public static Specification<Company> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }
}

