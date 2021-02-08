package org.example.k42un0k0force;

import org.example.k42un0k0force.infrastructure.db.AuthorityDao;
import org.example.k42un0k0force.infrastructure.db.AuthorityDto;
import org.example.k42un0k0force.infrastructure.db.EmployeeDao;
import org.example.k42un0k0force.infrastructure.db.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner bootstrap(EmployeeDao employeeDao, AuthorityDao authorityDao, PasswordEncoder encoder) {
        return (args) -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setUsername("hello");
            employeeDto.setPassword(encoder.encode("world"));
            employeeDao.save(employeeDto);
            AuthorityDto authorityDto = new AuthorityDto();
            authorityDto.setUsername("hello");
            authorityDto.setAuthority("ROLE_USER");
            authorityDao.save(authorityDto);
            log.info("create a employee and authority");
        };
    }
}