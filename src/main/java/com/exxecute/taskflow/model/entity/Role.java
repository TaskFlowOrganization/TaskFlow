package com.exxecute.taskflow.model.entity;

import com.exxecute.taskflow.model.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
