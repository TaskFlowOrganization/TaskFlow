package com.exxecute.taskflow.exception.found;

import com.exxecute.taskflow.exception.global.TaskFlowException;

/**
 * Custom Not Found Exception.
 *
 * @author Uladzislau Mikhayevich
 */
public class NotFoundException extends TaskFlowException {
    /**
     * Main message for the exception.
     */
    private static final String ERROR_MESSAGE = "Not found exception";

    /**
     * Exception constructor.
     * @param message Explanation message.
     * @param id Not found object id.
     */
    public NotFoundException(final String message, final Long id) {
        super(getMessage(message, id));
    }

    /**
     * Get message for exception.
     * @param message Explanation message.
     * @param id Not found object id.
     * @return Whole message for exception.
     */
    private static String getMessage(final String message, final Long id) {
        StringBuilder sb = new StringBuilder()
                .append(ERROR_MESSAGE)
                .append(" by id: ")
                .append(id)
                .append(" with message: `")
                .append(message)
                .append("`");
        return sb.toString();
    }
}
