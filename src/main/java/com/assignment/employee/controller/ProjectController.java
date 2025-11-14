package com.assignment.employee.controller;

import com.assignment.employee.entity.Employee;
import com.assignment.employee.entity.Project;
import com.assignment.employee.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByProjectId(@PathVariable Long projectId) {
        List<Employee> employees = projectService.getEmployeesByProjectId(projectId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/name/{projectName}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByProjectName(@PathVariable String projectName) {
        List<Employee> employees = projectService.getEmployeesByProjectName(projectName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Project>> getProjectsByEmployeeId(@PathVariable Long employeeId) {
        List<Project> projects = projectService.getProjectsByEmployeeId(employeeId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/employee/email/{employeeEmail}")
    public ResponseEntity<List<Project>> getProjectsByEmployeeEmail(@PathVariable String employeeEmail) {
        List<Project> projects = projectService.getProjectsByEmployeeEmail(employeeEmail);
        return ResponseEntity.ok(projects);
    }

    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Project> projects = projectService.getAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
