package org.orange.rampup.springbootstage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.internal.runners.JUnit4ClassRunner;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnit4ClassRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testAddEmployee() {
        String url = "/add/employee";
        Employee employee = new Employee("mkmk", "11/11/1111" , "male" , "11/11/1111" , "IS" ,
                "A"  , 10000 , 9900 , "YES" , null , null);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(createURLWithPort(url), employee, String.class);
        assertEquals(responseEntity.getStatusCodeValue() , 200 );
    }

    @Test
    public void testRetrieve(){
        String url = "/get/all/employees";
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity(createURLWithPort(url), String.class);
        assertEquals(responseEntity.getStatusCodeValue() , 200 );
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:8080"  + uri;
    }

}