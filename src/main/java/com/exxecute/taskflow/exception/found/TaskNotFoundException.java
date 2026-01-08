package com.exxecute.taskflow.exception.found;

public class TaskNotFoundException extends NotFoundException {
    private static final String ERROR_MESSAGE = "Task Not Found";

    public TaskNotFoundException(final Long id) {
        super(ERROR_MESSAGE, id);
    }
}
