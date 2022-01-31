package org.orange.rampup.springbootstage.security;

import org.orange.rampup.springbootstage.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDetails implements UserDetails {


    Employee employee;

    public EmployeeDetails(Employee employee){
        this.employee = employee;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(authority2);
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getDepartment();
    }

    @Override
    public String getUsername() {
        return employee.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
