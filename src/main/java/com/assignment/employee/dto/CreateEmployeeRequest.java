package com.assignment.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    // Convenience constructor for tests
    public CreateEmployeeRequest(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    @NotBlank(message = "Name is required")
    private String name;

    private Long addressId;
    
    private AddressRequest address;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressRequest {
        private String street;
        private String city;
        private String state;
        private String pinCode;
    }
}
