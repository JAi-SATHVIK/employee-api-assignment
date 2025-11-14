package com.assignment.employee.dto;

import java.util.Set;

public class CompanyResponse {
    private Long id;
    private String name;
    private Set<ProjectResponse> projects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectResponse> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectResponse> projects) {
        this.projects = projects;
    }
}

