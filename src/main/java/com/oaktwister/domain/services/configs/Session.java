package com.oaktwister.domain.services.configs;

import com.oaktwister.domain.models.drives.Drive;
import com.oaktwister.domain.models.drives.DriveMeta;

public class Session {

    private static Session instance;

    private Drive drive;
    private DriveMeta driveMeta;

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() { }

    public void setMeta(DriveMeta driveMeta) {
        this.driveMeta = driveMeta;
    }

    public DriveMeta getMeta() {
        return driveMeta;
    }

    public boolean hasMeta() {
        return driveMeta != null;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Drive getDrive() {
        return drive;
    }

    public boolean hasDrive() {
        return drive != null;
    }

}
