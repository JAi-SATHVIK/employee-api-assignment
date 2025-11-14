package com.assignment.employee.controller;

import com.assignment.employee.dto.CompanyResponse;
import com.assignment.employee.entity.Company;
import com.assignment.employee.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/name/{name}")
    public ResponseEntity<CompanyResponse> getCompanyByName(@PathVariable String name) {
        CompanyResponse response = companyService.getCompanyByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}/specifications")
    public ResponseEntity<CompanyResponse> getCompanyByNameUsingSpecifications(@PathVariable String name) {
        CompanyResponse response = companyService.getCompanyByNameUsingSpecifications(name);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{companyId}/with-cascade")
    public ResponseEntity<Void> deleteCompanyWithCascade(@PathVariable Long companyId) {
        companyService.deleteCompanyWithCascade(companyId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{companyId}/without-cascade")
    public ResponseEntity<Void> deleteCompanyWithoutCascade(@PathVariable Long companyId) {
        companyService.deleteCompanyWithoutCascade(companyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Company>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Company> companies = companyService.getAllCompanies(pageable);
        return ResponseEntity.ok(companies);
    }
}
