package com.oaktwister.services;

import com.oaktwister.models.aggregators.drives.Drive;

public class Context {

    private static Context instance;

    private Drive drive;

    private Context() {
    }

    public static Context getInstance() {
        if(instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

}
