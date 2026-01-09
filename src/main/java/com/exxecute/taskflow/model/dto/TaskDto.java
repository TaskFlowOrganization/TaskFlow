package com.exxecute.taskflow.model.dto;

import com.exxecute.taskflow.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for creating Task.
 * Used for incoming REST requests.
 *
 * @author Uladzislau Mikhayevich
 */
@Getter
@Setter
public class TaskDto {
    /**
     * Title of the task.
     * Max 255 symbols.
     */
    @NotNull(message = "Title must not be null")
    @Size(min = 1, max = 255, message = "Title length must be between 1 and 255 characters")
    private String title;

    /**
     * Description of the task.
     * Optional field.
     */
    @Size(max = 5000, message = "Description must be less than 5000 characters")
    private String description;

    /**
     * Status of the task.
     */
    @NotNull(message = "Status must not be null")
    private Status status;
}
