package com.exxecute.taskflow.logging;

public final class LoggerFactory {

    private LoggerFactory(){}

    public static AppLogger getLogger(Class<?> clazz) {
        return new Slf4jLogger(clazz);
    }
}
