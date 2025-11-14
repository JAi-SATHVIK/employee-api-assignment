package com.assignment.employee.service;

import com.assignment.employee.dto.CompanyResponse;
import com.assignment.employee.dto.ProjectResponse;
import com.assignment.employee.entity.Company;
import com.assignment.employee.entity.Project;
import com.assignment.employee.repository.CompanyRepository;
import com.assignment.employee.repository.CompanySpecifications;
import com.assignment.employee.repository.EmployeeRepository;
import com.assignment.employee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ProjectRepository projectRepository;

    public CompanyResponse getCompanyByName(String name) {
        Company company = companyRepository.findByNameWithProjectsAndEmployees(name)
                .orElseThrow(() -> new RuntimeException("Company not found with name: " + name));
        return mapToResponse(company);
    }

    public CompanyResponse getCompanyByNameUsingSpecifications(String name) {
        Specification<Company> spec = CompanySpecifications.hasName(name);
        Company company = companyRepository.findOne(spec)
                .orElseThrow(() -> new RuntimeException("Company not found with name: " + name));
        Company companyWithDetails = companyRepository.findByNameWithProjectsAndEmployees(name)
                .orElse(company);
        return mapToResponse(companyWithDetails);
    }


    public void deleteCompanyWithCascade(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
        

        Company companyWithDetails = companyRepository.findByNameWithProjectsAndEmployees(company.getName())
                .orElse(company);
        
        
        Set<com.assignment.employee.entity.Employee> allEmployees = new java.util.HashSet<>();
        for (Project project : companyWithDetails.getProjects()) {
         
            projectRepository.findByIdWithEmployees(project.getId())
                    .ifPresent(p -> allEmployees.addAll(p.getEmployees()));
        }
        

        for (Project project : companyWithDetails.getProjects()) {
            project.getEmployees().clear();
            projectRepository.save(project);
        }
        
        for (com.assignment.employee.entity.Employee employee : allEmployees) {
            employee.getProjects().clear(); 
            employeeRepository.delete(employee); 
        }
        
        companyRepository.delete(companyWithDetails);
    }

    public void deleteCompanyWithoutCascade(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
        
        Set<Project> projects = company.getProjects();
        for (Project project : projects) {
            project.setCompany(null);
        }
        company.getProjects().clear();
        
        companyRepository.delete(company);
    }

    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Page<Company> getAllCompaniesWithSpecification(Specification<Company> spec, Pageable pageable) {
        return companyRepository.findAll(spec, pageable);
    }

    private CompanyResponse mapToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        Set<ProjectResponse> projectResponses = company.getProjects().stream()
                .map(this::mapProjectToResponse)
                .collect(Collectors.toSet());
        response.setProjects(projectResponses);
        return response;
    }

    private ProjectResponse mapProjectToResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setEmployees(project.getEmployees());
        return response;
    }
}
