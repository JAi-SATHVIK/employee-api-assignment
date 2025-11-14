package com.assignment.employee.repository;

import com.assignment.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmailUsingHQL(@Param("email") String email);

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    Optional<Employee> findByNameUsingHQL(@Param("name") String name);

    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Optional<Employee> findByEmailUsingNativeSQL(@Param("email") String email);

    @Query(value = "SELECT * FROM employees WHERE name = :name", nativeQuery = true)
    Optional<Employee> findByNameUsingNativeSQL(@Param("name") String name);

    @Query("SELECT e FROM Employee e JOIN e.projects p WHERE p.id = :projectId")
    java.util.List<Employee> findEmployeesByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT e FROM Employee e JOIN e.projects p WHERE p.name = :projectName")
    java.util.List<Employee> findEmployeesByProjectName(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p JOIN p.employees e WHERE e.id = :employeeId")
    java.util.List<com.assignment.employee.entity.Project> findProjectsByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT p FROM Project p JOIN p.employees e WHERE e.email = :employeeEmail")
    java.util.List<com.assignment.employee.entity.Project> findProjectsByEmployeeEmail(@Param("employeeEmail") String employeeEmail);

    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<Employee> findByIdLazy(@Param("id") Long id);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.address WHERE e.id = :id")
    Optional<Employee> findByIdEager(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmailLazy(@Param("email") String email);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.address WHERE e.email = :email")
    Optional<Employee> findByEmailEager(@Param("email") String email);
}

