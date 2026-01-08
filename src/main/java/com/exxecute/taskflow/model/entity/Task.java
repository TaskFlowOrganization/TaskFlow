package com.exxecute.taskflow.model.entity;

import com.exxecute.taskflow.model.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Task entity.
 * Saves in DataBase.
 *
 * @author Uladzislau Mikhayevich
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tasks")
public class Task {
    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Version for database */
    @Version
    @Column(nullable = false)
    private Long version;

    /**
     * Title of the task.
     *
     * @see maximum size is 255 sumbols.
     */
    @Column(nullable = false, length = 255)
    private String title;

    /**
     * Text description of the task.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Status of the task, defined by enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * Date and Time of the task creation.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Method that handles at Object creating,
     * uses for setting creating Date and Time.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * String definition of id name.
     */
    public final static String ID_NAME = "id";
}