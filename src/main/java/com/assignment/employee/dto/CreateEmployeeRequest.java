package com.assignment.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateEmployeeRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private Long addressId;
    
    private AddressRequest address;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;
    
    public static class AddressRequest {
        private String street;
        private String city;
        private String state;
        private String pinCode;
        
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getPinCode() { return pinCode; }
        public void setPinCode(String pinCode) { this.pinCode = pinCode; }
    }

    public CreateEmployeeRequest() {
    }

    public CreateEmployeeRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CreateEmployeeRequest(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    
    public AddressRequest getAddress() {
        return address;
    }
    
    public void setAddress(AddressRequest address) {
        this.address = address;
    }
}
