package org.orange.rampup.springbootstage.service;

import org.orange.rampup.springbootstage.entity.Employee;
import org.orange.rampup.springbootstage.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> saveEmployees(List<Employee> employees){
        return employeeRepository.saveAll(employees);
    }

    public List<Employee> selectAllEmployee(){
        return employeeRepository.findAll();
    }

    public Employee selectEmployee(int id){
        return  employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> selectEmployeeByTeam(String Team){
        return employeeRepository.findByTeam(Team);
    }

    public String deleteEmployeeById(int id){
        employeeRepository.deleteById(id);
        return "Employee " + id + " Deleted";
    }

    public Employee updateEmployee(Employee employee){

        return employee;

    }


}
