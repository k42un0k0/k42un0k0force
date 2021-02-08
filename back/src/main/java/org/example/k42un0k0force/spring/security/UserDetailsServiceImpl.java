package org.example.k42un0k0force.spring.security;

import org.example.k42un0k0force.infrastructure.db.AuthorityDao;
import org.example.k42un0k0force.infrastructure.db.AuthorityDto;
import org.example.k42un0k0force.infrastructure.db.EmployeeDao;
import org.example.k42un0k0force.infrastructure.db.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeDto> employeeDto = employeeDao.findByUsername(username);
        Optional<AuthorityDto> authorityDto = authorityDao.findByUsername(username);
        if (employeeDto.isPresent() && authorityDto.isPresent()) {
            return new UserDetailsImpl(employeeDto.get(), authorityDto.get());
        }
        throw new UsernameNotFoundException(username);
    }
}
