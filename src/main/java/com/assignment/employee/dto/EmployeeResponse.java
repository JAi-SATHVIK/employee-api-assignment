package com.assignment.employee.dto;

import com.assignment.employee.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
}
