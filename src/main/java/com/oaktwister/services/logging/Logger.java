package com.oaktwister.services.logging;

public class Logger {

    private static final String BLACK_ANSI_COLOR = "\\u001B[30m";
    private static final String RED_ANSI_COLOR = "\\u001B[31m";
    private static final String GREEN_ANSI_COLOR = "\\u001B[32m";
    private static final String YELLOW_ANSI_COLOR = "\\u001B[33m";
    private static final String BLUE_ANSI_COLOR = "\\u001B[34m";
    private static final String CYAN_ANSI_COLOR = "\\u001B[36m";
    private static final String PURPLE_ANSI_COLOR = "\\u001B[35m";
    private static final String WHITE_ANSI_COLOR = "\\u001B[37m";
    private static final String ANSI_RESET = "\\u001B[0m";

    private static final String TRACE_PREFIX = "TRACE";
    private static final String DEBUG_PREFIX = "DEBUG";
    private static final String INFO_PREFIX = "INFO";
    private static final String WARN_PREFIX = "WARN";
    private static final String ERROR_PREFIX = "ERROR";
    private static final String CRITICAL_PREFIX = "CRITICAL";

    private final String caller;

    public Logger(Class<?> caller) {
        this.caller = caller.getTypeName();
    }

    private void log(String prefix, String message) {
        System.out.printf("[%s] %s %s%n", prefix, caller, message);
    }

    public void trace(String message) {
        log(TRACE_PREFIX, WHITE_ANSI_COLOR + message + ANSI_RESET);
    }

    public void trace(String message, Object... args) {
        trace(String.format(message, args));
    }

    public void debug(String message) {
        log(DEBUG_PREFIX, message);
    }

    public void debug(String message, Object... args) {
        debug(String.format(message, args));
    }

    public void info(String message) {
        log(INFO_PREFIX, CYAN_ANSI_COLOR + message + ANSI_RESET);
    }

    public void info(String message, Object... args) {
        info(String.format(message, args));
    }

    public void warn(String message) {
        log(WARN_PREFIX, YELLOW_ANSI_COLOR + message + ANSI_RESET);
    }

    public void warn(String message, Object... args) {
        warn(String.format(message, args));
    }

    public void error(String message) {
        log(ERROR_PREFIX, RED_ANSI_COLOR + message + ANSI_RESET);
    }

    public void error(String message, Object... args) {
        error(String.format(message, args));
    }

    public void error(Exception ex, String message) {
        error(message);
        ex.printStackTrace();
    }

    public void critical(String message) {
        log(CRITICAL_PREFIX, RED_ANSI_COLOR + message + ANSI_RESET);
    }

    public void critical(String message, Object... args) {
        critical(String.format(message, args));
    }

}
