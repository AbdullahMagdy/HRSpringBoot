package org.orange.rampup.springbootstage.repository;

import org.orange.rampup.springbootstage.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
public interface EmployeeRepository extends JpaRepository<Employee,Integer> , EmployeeCustomRepo {

    public Employee findByName(String name);

    public List<Employee> findByTeam(String team);

    @Query("select e.isManager from Employee e where e.id = ?1")
    public String checkIfManager(int id);

    @Query("select e.manager from Employee e where e.id = ?1")
    public Employee checkIfNullOrNot(int id);

    @Query("select e from Employee e where e.id = ?1")
    public Employee findEmp(int id);


    @Query(value = "\n" +
            "WITH recursive ancestors AS (\n" +
            "           SELECT cat.id, cat.name , cat.manager_id, 1 AS lvl \n" +
            "           FROM employees as cat \n" +
            "             WHERE cat.manager_id = :managerId \n" +
            "            UNION ALL \n" +
            "            SELECT parent.id, parent.name , parent.manager_id, child.lvl + 1 AS lvl \n" +
            "            FROM employees parent \n" +
            "             inner join ancestors as child \n" +
            "            ON parent.manager_id = child.id\n" +
            "          )\n" +
            "SELECT * from ancestors ORDER BY lvl" , nativeQuery = true)
    List<String> findAncestry2(@Param("managerId") int categoryId);


}

