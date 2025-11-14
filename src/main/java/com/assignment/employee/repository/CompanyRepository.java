package com.assignment.employee.repository;

import com.assignment.employee.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    @Query("SELECT c FROM Company c LEFT JOIN FETCH c.projects p LEFT JOIN FETCH p.employees WHERE c.name = :name")
    Optional<Company> findByNameWithProjectsAndEmployees(@Param("name") String name);
}