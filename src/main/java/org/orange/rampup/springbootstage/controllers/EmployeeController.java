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

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee){
        return  employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> retrieveEmployees(){
        return employeeService.selectAllEmployee();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee retrieveEmployee(@PathVariable int employeeId){
        return employeeService.selectEmployee(employeeId);
    }

    @GetMapping("employees/teams/{teamName}")
    public List<Employee> retrieveEmployeesByTeam(@PathVariable String teamName){
        return employeeService.selectEmployeeByTeam(teamName);
    }

    @GetMapping("employees/{employeeId}/remove")
    public String check(@PathVariable int employeeId){
        return  employeeRepository.customDeleteEmployee(employeeId);
    }

    @GetMapping("/employees/levels/{managerId}/recursive")
    public List<String> getRecuTrees(@PathVariable int managerId){
        return  employeeRepository.findAncestry2(managerId);
    }

}
