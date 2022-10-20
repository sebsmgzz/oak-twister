package com.oaktwister.services.config;

public class AppConfig {
    private static final String JAVA_ENVIRONMENT_KEY = "JAVA_ENVIRONMENT";

    private static AppConfig instance;

    private AppConfig() { }

    public static AppConfig getInstance() {
        if(instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public boolean isDevelopmentEnv() {
        String javaEnv =  System.getenv(JAVA_ENVIRONMENT_KEY);
        if(javaEnv == null) {
            return false;
        }
        return javaEnv.toLowerCase().startsWith("dev");
    }

}
