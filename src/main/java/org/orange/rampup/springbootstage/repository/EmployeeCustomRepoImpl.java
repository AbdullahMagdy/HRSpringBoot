package org.orange.rampup.springbootstage.repository;

import org.orange.rampup.springbootstage.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    EntityManager entityManager;

    public String customDeleteEmployee(int id) {

        String managerOrNot = employeeRepository.checkIfManager(id);
        Employee employee = employeeRepository.findEmp(id);
        Employee hisManager = employeeRepository.checkIfNullOrNot(id);
        if (managerOrNot.equalsIgnoreCase("YES")) {
            if (Objects.isNull(hisManager)){
                return "Cannot be deleted at all, It is a ceo";
            }else {
                List<Employee> list = employee.getManagedEmps();
                for (Employee emp : list){
                    emp.setManager(hisManager);
                    employeeRepository.save(emp);
                }
                employeeRepository.deleteById(id);
            }

        }else {
            employeeRepository.deleteById(id);
        }

        return "Succefully Deleted";

    }
}

