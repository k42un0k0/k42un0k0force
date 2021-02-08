package org.example.k42un0k0force.spring.security;

import org.example.k42un0k0force.infrastructure.db.AuthorityDto;
import org.example.k42un0k0force.infrastructure.db.EmployeeDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private EmployeeDto employeeDto;
    private AuthorityDto authorityDto;

    public UserDetailsImpl(EmployeeDto employeeDto, AuthorityDto authorityDto) {
        this.employeeDto = employeeDto;
        this.authorityDto = authorityDto;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> arr = new ArrayList<>(1);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authorityDto.getAuthority());
        arr.add(grantedAuthority);
        return arr;
    }

    @Override
    public String getPassword() {
        return employeeDto.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeDto.getUsername();
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
