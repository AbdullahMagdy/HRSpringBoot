package org.orange.rampup.springbootstage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.orange.rampup.springbootstage.entity.Employee;
import org.orange.rampup.springbootstage.repository.EmployeeRepository;
import org.orange.rampup.springbootstage.service.EmployeeService;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;


import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private EmployeeService employeeService;

    @Resource
    private EmployeeRepository employeeRepository;

    @Test
    void addEmployee() throws Exception {

        Employee employee = new Employee(1,"Ali", "11/11/1111" , "male" , "11/11/1111" , "IS" ,
                "A"  , 10000 , 9900 , "YES");

        employeeService.saveEmployee(employee);

        Employee employee2 = employeeService.selectEmployee(1);
        assertEquals("Ali", employee2.getName());
    }

    @Test
    void retrieveEmployees() {
    }

    @Test
    void retrieveEmployeesByTeam() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    public void get_allVehicles_returnsOkWithListOfVehicles() throws Exception {

        List<Employee> vehicleList = new ArrayList<>();
        Employee vehicle1 = new Employee(1,"Ali", "11/11/1111" , "male" , "11/11/1111" , "IS" ,
                "A"  , 10000 , 9900 , "YES");
        Employee vehicle2 = new Employee(2,"Alia", "11/11/1111" , "female" , "11/11/1111" , "IS" ,
                "A"  , 10000 , 9900 , "YES");
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

        employeeService.saveEmployees(vehicleList);

        List<Employee> list = employeeService.selectAllEmployee();

        assertEquals(vehicleList.size(), list.size());

    }



}