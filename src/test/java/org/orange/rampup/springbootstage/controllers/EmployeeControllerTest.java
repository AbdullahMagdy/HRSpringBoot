package org.orange.rampup.springbootstage.controllers;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.orange.rampup.springbootstage.entity.Employee;
import org.orange.rampup.springbootstage.repository.EmployeeRepository;
import org.orange.rampup.springbootstage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @ExpectedDatabase("/expectedData.xml")
    public void shouldAddEmployee() throws Exception {

        String employee = "{\"name\":\"Ali\",\"birthDay\":\"10/1/2010\",\"gender\":\"male\"," +
                "\"graduationDate\":\"10/10/2020\",\"department\":\"IS\",\"team\":\"A\"," +
                "\"grossSalary\":12000.0,\"netSalary\":10000.0,\"isManager\":\"NO\"," +
                "\"managedEmps\":[]}";
        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(employee));

    }

    @Test
    @DatabaseSetup("/sampleData.xml")
    public void shouldRetrieveAllEmployees() throws Exception {

           mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$.[0].name" , is("Ali")))
                   .andExpect(jsonPath("$.[1].name" , is("Ahmed")));
    }


    @Test
    @DatabaseSetup("/sampleData.xml")
    @ExpectedDatabase("/expectedData.xml")
    public void shouldDeleteEmployee() throws Exception {

        mockMvc.perform(get("/employees/2/remove").contentType(MediaType.APPLICATION_JSON));

    }


}