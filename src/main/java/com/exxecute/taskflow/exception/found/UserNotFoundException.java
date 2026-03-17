package com.exxecute.taskflow.exception.found;

public class UserNotFoundException extends NotFoundException {

        private static final String ERROR_MESSAGE = "User Not Found";

        public UserNotFoundException(final Long id) {
            super (ERROR_MESSAGE, id);
        }

        public UserNotFoundException(String field, String value) {
            super(ERROR_MESSAGE + " by" + field + " :" + value, null);
        }

}
