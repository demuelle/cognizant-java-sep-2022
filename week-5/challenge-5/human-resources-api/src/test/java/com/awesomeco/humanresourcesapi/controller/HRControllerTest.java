package com.awesomeco.humanresourcesapi.controller;

import com.awesomeco.humanresourcesapi.EmployeeFeignClient;
import com.awesomeco.humanresourcesapi.models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HRController.class)
public class HRControllerTest {
    @MockBean
    private EmployeeFeignClient employeeApi;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        Employee employeeWithoutId = new Employee();
        employeeWithoutId.setName("Mark Flark");
        employeeWithoutId.setJobTitle("Widget Generator");
        employeeWithoutId.setStartYear(1989);

        Employee employeeWithId = new Employee();
        employeeWithId.setId(55L);
        employeeWithId.setName("Mark Flark");
        employeeWithId.setJobTitle("Widget Generator");
        employeeWithId.setStartYear(1989);

        //Employee employee = employeeApi.getEmployeeById(id);
//        doReturn(employeeWithId).when(employeeApi).getEmployeeById(55L);
        when(employeeApi.getEmployeeById(55L)).thenReturn(employeeWithId);
        //employeeApi.hireEmployee(employee);

        Employee employeePreSave = new Employee();
        employeePreSave.setName("Julia Gulia");
        employeePreSave.setJobTitle("SprocketMaker");
        employeePreSave.setStartYear(2022);

        Employee newlyHiredEmployee = new Employee();
        newlyHiredEmployee.setId(587L);
        newlyHiredEmployee.setName("Julia Gulia");
        newlyHiredEmployee.setJobTitle("SprocketMaker");
        newlyHiredEmployee.setStartYear(2022);

        doReturn(newlyHiredEmployee).when(employeeApi).hireEmployee(employeePreSave);
    }

    @Test
    public void shouldReturnEmployeeInformationAnd200WhenGetEmployeeById() throws Exception {
        // Arrange
        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(55L);
        expectedEmployee.setName("Mark Flark STAR EMPLOYEE!!!");
        expectedEmployee.setJobTitle("Widget Generator");
        expectedEmployee.setStartYear(1989);

        String jsonExpectedOutput = mapper.writeValueAsString(expectedEmployee);

        // Act
        mockMvc.perform(get("/hr/employee/{id}", 55))
                .andExpect(status().isOk())                     // Assert
                .andExpect(content().json(jsonExpectedOutput)); // Assert
    }

    @Test
    public void shouldReturn201AndEmployeeWithCurrentStartDateAndPopulatedIdOnPostRequest() throws Exception {
        // Arrange
        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(587L);
        expectedEmployee.setName("Julia Gulia");
        expectedEmployee.setJobTitle("SprocketMaker");
        expectedEmployee.setStartYear(2022);

        Employee employeeParameterToPostRequest = new Employee();
        employeeParameterToPostRequest.setName("Julia Gulia");
        employeeParameterToPostRequest.setJobTitle("SprocketMaker");

        String outputJson = mapper.writeValueAsString(expectedEmployee);
        String inputJson = mapper.writeValueAsString(employeeParameterToPostRequest);

        // Act
        mockMvc.perform(post("/hr/employee")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }


}