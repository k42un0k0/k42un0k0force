package org.example.k42un0k0force.infrastructure.db;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityDao extends CrudRepository<AuthorityDto, AuthorityDtoPK> {
    Optional<AuthorityDto> findByUsername(String userName);
}