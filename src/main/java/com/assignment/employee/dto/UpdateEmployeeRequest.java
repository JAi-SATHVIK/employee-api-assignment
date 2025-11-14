package com.assignment.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private String lastName;
    private String phone;
    private Long addressId;
    
    private AddressUpdateRequest address;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressUpdateRequest {
        private String street;
        private String city;
        private String state;
        private String pinCode;
    }
}
