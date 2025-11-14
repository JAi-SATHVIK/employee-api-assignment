package com.assignment.employee.repository;

import com.assignment.employee.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.employees WHERE p.id = :id")
    Optional<Project> findByIdWithEmployees(@Param("id") Long id);

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.employees WHERE p.name = :name")
    Optional<Project> findByNameWithEmployees(@Param("name") String name);
}