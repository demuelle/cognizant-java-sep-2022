package com.awesomeco.humanresourcesapi;

import com.awesomeco.humanresourcesapi.models.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "employee-api")
public interface EmployeeFeignClient {
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id);

    @PostMapping("/employee")
    public Employee hireEmployee(@RequestBody Employee employee);
}
