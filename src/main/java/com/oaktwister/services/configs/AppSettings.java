package com.oaktwister.services.configs;

public class AppSettings {

    private static AppSettings instance;

    private AppSettings() { }

    public static AppSettings getInstance() {
        if(instance == null) {
            instance = new AppSettings();
        }
        return instance;
    }

    public void addEncryptionKey() {
    }



}
