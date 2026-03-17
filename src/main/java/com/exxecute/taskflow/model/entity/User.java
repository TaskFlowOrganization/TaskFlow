package com.exxecute.taskflow.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * User entity.
 * Saves in DataBase.
 *
 * @author Uladzislau Mikhayevich
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * User email.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User tasks.
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> tasks;
}
