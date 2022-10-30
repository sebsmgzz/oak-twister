package com.oaktwister.services.configs;

import com.oaktwister.models.drives.Drive;
import com.oaktwister.models.drives.DriveMeta;

public class SessionSettings {

    private Drive drive;
    private DriveMeta driveMeta;

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
