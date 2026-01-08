package com.exxecute.taskflow.exception.found;

/**
 * Custom Task Not Found Exception.
 *
 * @author Uladzislau Mikhayevich
 */
public class TaskNotFoundException extends NotFoundException {
    /**
     * Main message for the exception.
     */
    private static final String ERROR_MESSAGE = "Task Not Found";

    /**
     * Exception constructor.
     * @param id Not found task id.
     */
    public TaskNotFoundException(final Long id) {
        super(ERROR_MESSAGE, id);
    }
}
