package com.oaktwister.app.services.configs;

public class Settings {

    private static Settings instance;

    private Settings() { }

    public static Settings getInstance() {
        if(instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void addEncryptionKey() {
    }

}
