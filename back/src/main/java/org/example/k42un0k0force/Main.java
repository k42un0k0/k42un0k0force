package org.example.k42un0k0force;

import org.example.k42un0k0force.infrastructure.db.AuthorityDao;
import org.example.k42un0k0force.infrastructure.db.AuthorityDto;
import org.example.k42un0k0force.infrastructure.db.EmployeeDao;
import org.example.k42un0k0force.infrastructure.db.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
//@EntityScan("com.example.k42un0k0force.infrastructure.db")
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    public CommandLineRunner createEmployee(EmployeeDao employeeDao, AuthorityDao authorityDao, PasswordEncoder encoder) {
        return (args) -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setUsername("username");
            employeeDto.setPassword(encoder.encode("password"));
            employeeDao.save(employeeDto);
            AuthorityDto authorityDto = new AuthorityDto();
            authorityDto.setUsername("username");
            authorityDto.setAuthority("ROLE_USER");
            authorityDao.save(authorityDto);
            log.info("create a employee and authority");
        };
    }
}