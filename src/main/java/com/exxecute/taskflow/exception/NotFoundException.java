package com.exxecute.taskflow.exception;

/**
 * Custom Not Found Exception.
 *
 * @author Uladzislau Mikhayevich
 */
public class NotFoundException extends RuntimeException {

    /**
     * exception constructor.
     * @param message message for exception.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
