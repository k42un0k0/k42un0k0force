package org.example.k42un0k0force.infrastructure.db;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "authorities")
@IdClass(AuthorityPk.class)
public class AuthorityDto {
    @Setter
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String username;

    @Setter
    @Getter
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String authority;

}
