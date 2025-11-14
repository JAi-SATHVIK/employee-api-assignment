package com.assignment.employee.repository;

import com.assignment.employee.entity.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecifications {
    public static Specification<Project> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }
}

