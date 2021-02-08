package org.example.k42un0k0force.infrastructure.db;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeDao extends CrudRepository<EmployeeDto, String> {
    Optional<EmployeeDto> findByUsername(String userName);
}
