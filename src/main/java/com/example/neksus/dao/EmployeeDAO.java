package com.example.neksus.dao;

import com.example.neksus.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        employee.setCity(rs.getString("CITY"));
        employee.setCountry(rs.getString("COUNTRY"));
        employee.setStreet(rs.getString("STREET"));
        employee.setHouseNumber(rs.getString("HOUSE_NUMBER"));
        employee.setPostalCode(rs.getString("POSTAL_CODE"));
        return employee;
    };

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM EMPLOYEE";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    public Employee getEmployeeByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM EMPLOYEE WHERE PHONE_NUMBER = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{phoneNumber}, employeeRowMapper);
    }

    public boolean insertEmployee(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, CITY, COUNTRY, STREET, HOUSE_NUMBER, POSTAL_CODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getPhoneNumber(), employee.getCity(), employee.getCountry(), employee.getStreet(), employee.getHouseNumber(), employee.getPostalCode());
        return rowsAffected > 0;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE EMPLOYEE SET FIRST_NAME = ?, LAST_NAME = ?, CITY = ?, COUNTRY = ?, STREET = ?, HOUSE_NUMBER = ?, POSTAL_CODE = ? WHERE PHONE_NUMBER = ?";
        int rowsAffected = jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getCity(), employee.getCountry(), employee.getStreet(), employee.getHouseNumber(), employee.getPostalCode(), employee.getPhoneNumber());
        return rowsAffected > 0;
    }

    public boolean deleteEmployee(String phoneNumber) {
        String sql = "DELETE FROM EMPLOYEE WHERE PHONE_NUMBER = ?";
        int rowsAffected = jdbcTemplate.update(sql, phoneNumber);
        return rowsAffected > 0;
    }
}

