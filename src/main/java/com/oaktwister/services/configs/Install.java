package com.oaktwister.services.configs;

public class Install {

    private static Install instance;

    private Install() { }

    public static Install getInstance() {
        if(instance == null) {
            instance = new Install();
        }
        return instance;
    }

    public void addEncryptionKey() {
    }



}
