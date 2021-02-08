package org.example.k42un0k0force.infrastructure.db;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuthorityPk implements Serializable {
    private String username;

    private String authority;

    public AuthorityPk() {

    }

    public AuthorityPk(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
