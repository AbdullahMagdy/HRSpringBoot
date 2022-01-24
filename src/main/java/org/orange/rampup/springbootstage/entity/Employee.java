package org.orange.rampup.springbootstage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String name;
    private String birthDay;
    private String gender;
    private String graduationDate;
    private String department;
    private String team;
    private double grossSalary;
    private double netSalary;
    private String isManager;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<Employee> managedEmps;

    public Employee(String name, String birthDay, String gender,
                    String graduationDate, String department, String team, Employee manager_id ,double grossSalary, double netSalary, String isManager) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.department = department;
        this.team = team;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.isManager = isManager;
        this.manager = manager_id;
    }
    public Employee(String name, String birthDay, String gender,
                   String graduationDate, String department, String team ,double grossSalary, double netSalary, String isManager) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.department = department;
        this.team = team;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.isManager = isManager;
    }
    public Employee(int id, String name, String birthDay, String gender,
                    String graduationDate, String department, String team ,double grossSalary, double netSalary, String isManager) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.department = department;
        this.team = team;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.isManager = isManager;
        this.id = id;
    }

    public Employee(int id , String name, String birthDay, String gender,
                    String graduationDate, String department, String team ,double grossSalary, double netSalary, String isManager, Employee manager_id , List<Employee> list) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.department = department;
        this.team = team;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.isManager = isManager;
        this.manager = manager_id;
        this.managedEmps = list;
    }

    public Employee( String name, String birthDay, String gender,
                    String graduationDate, String department, String team ,double grossSalary, double netSalary, String isManager, Employee manager_id , List<Employee> list) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.department = department;
        this.team = team;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.isManager = isManager;
        this.manager = manager_id;
        this.managedEmps = list;
    }

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getGender() {
        return gender;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getTeam() {
        return team;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setManagedEmps(List<Employee> managedEmps) {
        this.managedEmps = managedEmps;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getManagedEmps() {
        return managedEmps;
    }
}