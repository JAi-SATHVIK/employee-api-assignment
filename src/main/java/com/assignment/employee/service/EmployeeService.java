package com.assignment.employee.service;

import com.assignment.employee.dto.CreateEmployeeRequest;
import com.assignment.employee.dto.EmployeeResponse;
import com.assignment.employee.dto.UpdateEmployeeRequest;
import com.assignment.employee.dto.UpdatePhoneRequest;
import com.assignment.employee.entity.Address;
import com.assignment.employee.entity.Employee;
import com.assignment.employee.repository.AddressRepository;
import com.assignment.employee.repository.EmployeeRepository;
import com.assignment.employee.repository.EmployeeSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Employee employee = new Employee(request.getName(), request.getEmail(), request.getPhone());

        if (request.getAddress() != null) {
            Address address = new Address();
            address.setStreet(request.getAddress().getStreet());
            address.setCity(request.getAddress().getCity());
            address.setState(request.getAddress().getState());
            address.setPinCode(request.getAddress().getPinCode());
            employee.setAddress(address);

        } else if (request.getAddressId() != null) {
            addressRepository.findById(request.getAddressId()).ifPresent(employee::setAddress);
        }

        Employee saved = employeeRepository.save(employee);
        return mapToResponse(saved);
    }

    public EmployeeResponse getEmployeeByEmailUsingSpecifications(String email) {
        Specification<Employee> spec = EmployeeSpecifications.hasEmail(email);
        Employee employee = employeeRepository.findOne(spec)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByEmailUsingHQL(String email) {
        Employee employee = employeeRepository.findByEmailUsingHQL(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByEmailUsingNativeSQL(String email) {
        Employee employee = employeeRepository.findByEmailUsingNativeSQL(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByNameUsingSpecifications(String name) {
        Specification<Employee> spec = EmployeeSpecifications.hasName(name);
        Employee employee = employeeRepository.findOne(spec)
                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByNameUsingHQL(String name) {
        Employee employee = employeeRepository.findByNameUsingHQL(name)
                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByNameUsingNativeSQL(String name) {
        Employee employee = employeeRepository.findByNameUsingNativeSQL(name)
                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
        return mapToResponse(employee);
    }

    public EmployeeResponse updateEmployee(String email, UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findByEmailUsingHQL(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));

        if (request.getLastName() != null) {
            employee.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            employee.setPhone(request.getPhone());
        }

        if (request.getAddress() != null) {
            Address address;
            if (employee.getAddress() != null) {

                address = employee.getAddress();
                if (request.getAddress().getStreet() != null) {
                    address.setStreet(request.getAddress().getStreet());
                }
                if (request.getAddress().getCity() != null) {
                    address.setCity(request.getAddress().getCity());
                }
                if (request.getAddress().getState() != null) {
                    address.setState(request.getAddress().getState());
                }
                if (request.getAddress().getPinCode() != null) {
                    address.setPinCode(request.getAddress().getPinCode());
                }
            } else {

                address = new Address();
                address.setStreet(request.getAddress().getStreet());
                address.setCity(request.getAddress().getCity());
                address.setState(request.getAddress().getState());
                address.setPinCode(request.getAddress().getPinCode());
                employee.setAddress(address);
            }
        } else if (request.getAddressId() != null) {

            addressRepository.findById(request.getAddressId())
                    .ifPresentOrElse(employee::setAddress, () -> {
                        throw new RuntimeException("Address not found with id: " + request.getAddressId());
                    });
        }

        Employee updated = employeeRepository.save(employee);
        return mapToResponse(updated);
    }

    public EmployeeResponse updateEmployeePhone(String email, UpdatePhoneRequest request) {
        Employee employee = employeeRepository.findByEmailUsingHQL(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));

        employee.setPhone(request.getPhone());

        Employee updated = employeeRepository.save(employee);
        return mapToResponse(updated);
    }

    public void deleteEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmailUsingHQL(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
        employeeRepository.delete(employee);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> getAllEmployeesWithSpecification(Specification<Employee> spec, Pageable pageable) {
        return employeeRepository.findAll(spec, pageable);
    }

    public EmployeeResponse getEmployeeByIdLazy(Long id) {
        Employee employee = employeeRepository.findByIdLazy(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        if (employee.getAddress() != null) {
            employee.getAddress().getStreet();
        }
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByIdEager(Long id) {
        Employee employee = employeeRepository.findByIdEager(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByEmailLazy(String email) {
        Employee employee = employeeRepository.findByEmailLazy(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));

        if (employee.getAddress() != null) {
            employee.getAddress().getStreet();
        }
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByEmailEager(String email) {
        Employee employee = employeeRepository.findByEmailEager(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
        return mapToResponse(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setAddress(employee.getAddress());
        return response;
    }
}
