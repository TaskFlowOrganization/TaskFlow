package com.exxecute.taskflow.logging;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public abstract class AbstractLogger {

    protected final Logger log;

    protected AbstractLogger(Class<?> clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    protected void info(String message) {
        log.info(message);
    }

    protected void warn(String message) {
        log.warn(message);
    }

    protected void error(String message, Throwable ex) {
        log.error(message, ex);
    }

    protected void debug(String message) {
        log.debug(message);
    }


}
