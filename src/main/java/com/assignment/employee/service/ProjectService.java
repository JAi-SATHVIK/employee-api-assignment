package com.assignment.employee.service;

import com.assignment.employee.entity.Employee;
import com.assignment.employee.entity.Project;
import com.assignment.employee.repository.EmployeeRepository;
import com.assignment.employee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesByProjectId(Long projectId) {
        return employeeRepository.findEmployeesByProjectId(projectId);
    }

    public List<Employee> getEmployeesByProjectName(String projectName) {
        return employeeRepository.findEmployeesByProjectName(projectName);
    }

    public List<Project> getProjectsByEmployeeId(Long employeeId) {
        return employeeRepository.findProjectsByEmployeeId(employeeId);
    }

    public List<Project> getProjectsByEmployeeEmail(String employeeEmail) {
        return employeeRepository.findProjectsByEmployeeEmail(employeeEmail);
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Page<Project> getAllProjectsWithSpecification(Specification<Project> spec, Pageable pageable) {
        return projectRepository.findAll(spec, pageable);
    }

    

    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        
        project.getEmployees().forEach(employee -> employee.getProjects().remove(project));
        project.getEmployees().clear();
        

        projectRepository.delete(project);
    }
}
