package com.exxecute.taskflow.exception.global;

/**
 * Custom Not Found Exception.
 *
 * @author Uladzislau Mikhayevich
 */
public class TaskFlowException extends RuntimeException {

    /**
     * exception constructor.
     * @param message message for exception.
     */
    public TaskFlowException(String message) {
        super(message);
    }
}
