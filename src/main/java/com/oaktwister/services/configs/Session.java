package com.oaktwister.services.configs;

import com.oaktwister.models.drives.Drive;
import com.oaktwister.models.drives.Version;

import java.util.UUID;

public class Session {

    private static Session instance;

    private Drive drive;

    private Session() { }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public UUID getLocation() {
        return drive.props().getId();
    }

    public Version getVersion() {
        return drive.props().getVersion();
    }

}
