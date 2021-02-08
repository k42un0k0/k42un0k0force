package org.example.k42un0k0force.infrastructure.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "employees")
public class EmployeeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String username;

    @Setter
    @Getter
    private String password;
}
