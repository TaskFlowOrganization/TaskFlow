package com.exxecute.taskflow.exception.responce;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Structure for Error response,
 * uses in Global Exception Handler for response returning.
 *
 * @author Uladzislau Mikhayevich
 */
@Getter
public class ErrorResponse {
    /**
     * Error status.
     */
    private final int status;

    /**
     * Error phrase.
     */
    private final String error;

    /**
     * Error body message.
     */
    private final String message;

    /**
     * Error created time.
     */
    private final LocalDateTime createdAt;

    private Map<String, String> fieldErrors;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public ErrorResponse(int status, String error, String message, Map<String, String> fieldErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.fieldErrors = fieldErrors;
    }
}