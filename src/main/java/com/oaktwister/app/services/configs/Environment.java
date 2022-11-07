package com.oaktwister.app.services.configs;

public class Environment {

    private static final String JAVA_ENVIRONMENT_KEY = "JAVA_ENVIRONMENT";

    private static Environment instance;

    private Environment() { }

    public static Environment getInstance() {
        if(instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public boolean isDevelopment() {
        String javaEnv = System.getenv(JAVA_ENVIRONMENT_KEY);
        if(javaEnv == null) {
            return false;
        }
        return javaEnv.toLowerCase().startsWith("dev");
    }

}
