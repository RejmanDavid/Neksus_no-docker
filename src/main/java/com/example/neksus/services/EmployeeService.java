package com.example.neksus.services;

import com.example.neksus.dao.EmployeeDAO;
import com.example.neksus.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeByPhoneNumber(String phoneNumber) {
        return employeeDAO.getEmployeeByPhoneNumber(phoneNumber);
    }

    public boolean addEmployee(Employee employee) {
        if (isEmployeeValid(employee)) {
            return employeeDAO.insertEmployee(employee);
        } else {
            throw new IllegalArgumentException("Invalid employee data.");
        }
    }

    public boolean updateEmployee(Employee employee) {
        if (isEmployeeValid(employee)) {
            return employeeDAO.updateEmployee(employee);
        } else {
            throw new IllegalArgumentException("Invalid employee data.");
        }
    }

    public boolean deleteEmployee(String phoneNumber) {
        return employeeDAO.deleteEmployee(phoneNumber);
    }

    private boolean isEmployeeValid(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            return false;
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            return false;
        }

        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().trim().isEmpty()) {
            return false;
        }

        if (employee.getCity() == null || employee.getCity().trim().isEmpty()) {
            return false;
        }

        if (employee.getCountry() == null || employee.getCountry().trim().isEmpty()) {
            return false;
        }

        if (employee.getStreet() == null || employee.getStreet().trim().isEmpty()) {
            return false;
        }

        if (employee.getHouseNumber() == null || employee.getHouseNumber().trim().isEmpty()) {
            return false;
        }

        if (employee.getPostalCode() == null || employee.getPostalCode().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
