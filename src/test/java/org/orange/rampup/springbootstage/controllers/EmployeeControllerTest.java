package org.orange.rampup.springbootstage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.ResultMatcher;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(JUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRetrieveEmployeeWithId() throws Exception {
        this.mockMvc.perform(get("/employees/1")).andDo(print()).andExpect(jsonPath("$.id", is(1))).
                andExpect(jsonPath("$.name", is("Fatma"))).
                andExpect(jsonPath("$.gender", is("female")));
    }

    @Test
    /*@SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD , scripts = "/test.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    })
     */
    public void shouldRetrieveAllEmployees() throws Exception {
        this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(
                jsonPath("$").isArray()).andExpect(jsonPath("$.[0].id", is(1))).
                andExpect(jsonPath("$.[0].name", is("Fatma"))).
                andExpect(jsonPath("$.[0].gender", is("female")));
    }

    @Test
    public void shouldAddEmployee() throws Exception {
        String newEmployee = "{\"name\":\"Adama\",\"birthDay\":\"11/11/1998\",\"gender\":\"male\",\"graduationDate\":\"10/5/2020\",\"department\":\"IS\",\"team\":\"AB\",\"grossSalary\":12000.0,\"netSalary\":10000.0,\"isManager\":\"NO\",\"managedEmps\":[]}";
        this.mockMvc.perform(post("/employee").content(newEmployee).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.id", is(greaterThan(0)))).
                andExpect(jsonPath("$.name", is("Adama"))).
                andExpect(jsonPath("$.gender", is("male")));
    }

    @Test
    public void shouldDeleteEmployeeWithId() throws Exception {

        String returnMessage  = this.mockMvc.perform(get("/employees/11/remove")).andDo(print()).andReturn().getResponse().getContentAsString();
        assertEquals("Succefully Deleted" , returnMessage);

    }




}