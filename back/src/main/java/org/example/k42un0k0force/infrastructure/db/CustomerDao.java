package org.example.k42un0k0force.infrastructure.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<CustomerDto, Long> {

    List<CustomerDto> findByLastName(String lastName);

    CustomerDto findById(long id);
}