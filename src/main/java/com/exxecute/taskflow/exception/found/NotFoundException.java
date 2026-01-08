package com.exxecute.taskflow.exception.found;

import com.exxecute.taskflow.exception.global.TaskFlowException;

public class NotFoundException extends TaskFlowException {
    private static final String ERROR_MESSAGE = "Not found exception";

    public NotFoundException(final String message, final Long id) {
        super(getMessage(message, id));
    }

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
