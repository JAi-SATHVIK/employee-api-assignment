package com.assignment.employee.dto;

public class UpdateEmployeeRequest {
    private String lastName;
    private String phone;
    private Long addressId;
    
    private AddressUpdateRequest address;

    public static class AddressUpdateRequest {
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

    public UpdateEmployeeRequest() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    public AddressUpdateRequest getAddress() {
        return address;
    }
    
    public void setAddress(AddressUpdateRequest address) {
        this.address = address;
    }
}
