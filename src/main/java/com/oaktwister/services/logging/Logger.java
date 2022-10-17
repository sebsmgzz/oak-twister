package com.oaktwister.services.logging;

public class Logger {

    private static final String INFO_PREFIX = "INFO";
    private static final String WARN_PREFIX = "WARN";
    private static final String ERROR_PREFIX = "ERROR";
    private static final String DEBUG_PREFIX = "DEBUG";
    private static final String TRACE_PREFIX = "TRACE";
    private static final String CRITICAL_PREFIX = "CRITICAL";

    private final String caller;

    public Logger(Class<?> caller) {
        this.caller = caller.getTypeName();
    }

    private void log(String prefix, String message) {
        System.out.printf("[%s] %s %s%n", prefix, caller, message);
    }

    public void info(String message) {
        log(INFO_PREFIX, message);
    }

    public void warn(String message) {
        log(WARN_PREFIX, message);
    }

    public void error(String message) {
        log(ERROR_PREFIX, message);
    }

    public void error(String message, Object... args) {
        error(String.format(message, args));
    }

    public void error(Exception ex, String message) {
        error(message);
        ex.printStackTrace();
    }

    public void debug(String message) {
        log(DEBUG_PREFIX, message);
    }

    public void debug(String message, Object... args) {
        debug(String.format(message, args));
    }

    public void trace(String message) {
        log(TRACE_PREFIX, message);
    }

    public void trace(String message, Object... args) {
        trace(String.format(message, args));
    }

    public void critical(String message) {
        log(CRITICAL_PREFIX, message);
    }

    public void critical(String message, Object... args) {
        critical(String.format(message, args));
    }

}
