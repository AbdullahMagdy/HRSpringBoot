package org.orange.rampup.springbootstage.security;

import org.orange.rampup.springbootstage.entity.Employee;
import org.orange.rampup.springbootstage.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Employee employee =  employeeRepository.findByName(name);
        if (employee == null) {
            throw new UsernameNotFoundException(name);
        }
        MyUserDetails myUserDetails = new MyUserDetails(employee);
        return myUserDetails;
    }

    private Collection<GrantedAuthority> getGrantedAuthority(Employee employee){

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(employee.getName().equalsIgnoreCase("admin")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

}