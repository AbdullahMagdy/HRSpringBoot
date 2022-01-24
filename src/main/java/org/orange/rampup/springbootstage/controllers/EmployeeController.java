package org.orange.rampup.springbootstage.controllers;


import org.orange.rampup.springbootstage.entity.Employee;
import org.orange.rampup.springbootstage.repository.EmployeeCustomRepo;
import org.orange.rampup.springbootstage.repository.EmployeeRepository;
import org.orange.rampup.springbootstage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCustomRepo employeeCustomRepo;


    @PostMapping("/add/employee")
    public Employee addEmployee(@RequestBody Employee employee){
        return  employeeService.saveEmployee(employee);
    }

    @GetMapping("get/all/employees")
    public List<Employee> retrieveEmployees(){
        return employeeService.selectAllEmployee();
    }

    @GetMapping("get/employee/{id}")
    public Employee retrieveEmployee(@PathVariable int id){
        return employeeService.selectEmployee(id);
    }

    @GetMapping("get/all/by/team/{Team}")
    public List<Employee> retrieveEmployeesByTeam(@PathVariable String Team){
        return employeeService.selectEmployeeByTeam(Team);
    }

    @GetMapping("delete/employee/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("check/{id}")
    public String check(@PathVariable int id){
        //return employeeRepository.check(id);
        return  employeeRepository.checkaya(id);
    }

    @GetMapping("recursive/tree/start/with/{id}")
    public List<String> getRecuTrees(@PathVariable int id){
        //return employeeRepository.check(id);
        return  employeeRepository.findAncestry2(id);
    }



}


/*
employee.setName("Abdullah");
        employee.setDepartment("IS");
        employee.setBirthDay("121313");
        employee.setGender("male");
        employee.setGraduationDate("234234");
        employee.setGrossSalary(1213132d);
        employee.setNetSalary(12324234d);
        employee.setIsManager("YES");
        employee.setManagerId(1);
        employee.setTeam("AB");
 */