package org.example.k42un0k0force.infrastructure.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuthorityDtoPK implements Serializable {
    @Column(columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(columnDefinition = "VARCHAR(50)")
    private String authority;
}
