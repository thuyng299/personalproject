package com.nonit.personalproject.security.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {
    private Long employeeId;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Employee employee) {
        Role role = employee.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority roles = new SimpleGrantedAuthority(role.name());
        authorities.add(roles);

        return new UserDetailsImpl(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(employeeId, user.employeeId);
    }

}
