package com.awesomeco.employeeapi.controller;

import com.awesomeco.employeeapi.repository.EmployeeRepository;
import com.awesomeco.employeeapi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repo.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Optional<Employee> returnVal = repo.findById(id);
        if (returnVal.isPresent() == false) {
            throw new RuntimeException("Invalid id: " + id);
        }
        return returnVal.get();
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        repo.save(employee);
    }
}
