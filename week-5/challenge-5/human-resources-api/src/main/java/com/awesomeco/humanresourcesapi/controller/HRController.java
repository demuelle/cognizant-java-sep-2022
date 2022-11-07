package com.awesomeco.humanresourcesapi.controller;

import com.awesomeco.humanresourcesapi.EmployeeFeignClient;
import com.awesomeco.humanresourcesapi.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {
    @Autowired
    private EmployeeFeignClient employeeApi;

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeApi.getEmployeeById(id);
        employee.setName(employee.getName() + " STAR EMPLOYEE!!!");
        return employee;
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee hire(@RequestBody Employee employee) {
        employee.setStartYear(2022); // TODO: make this dynamic to use the current year
        return employeeApi.hireEmployee(employee);
    }
}
