package com.oaktwister.domain.services.configs;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.drives.Drive;
import com.oaktwister.domain.models.drives.MetaDrive;

public class Session {

    public static String DRIVE_SESSION_PROPERTY_NAME = "Drive";
    public static String META_DRIVE_SESSION_PROPERTY_NAME = "MetaDrive";
    private static Session instance;

    private Drive drive;
    private MetaDrive metaDrive;

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() { }

    public boolean hasMetaDrive() {
        return metaDrive != null;
    }
    public MetaDrive tryGetMetaDrive() {
        return metaDrive;
    }
    public MetaDrive getMetaDrive() throws InvalidSessionPropertyException {
        if(hasMetaDrive()) {
            return metaDrive;
        }
        throw new InvalidSessionPropertyException(META_DRIVE_SESSION_PROPERTY_NAME);
    }
    public void setMetaDrive(MetaDrive metaDrive) {
        this.metaDrive = metaDrive;
    }

    public boolean hasDrive() {
        return drive != null;
    }
    public Drive tryGetDrive() {
        return drive;
    }
    public Drive getDrive() throws InvalidSessionPropertyException {
        if(hasDrive()) {
            return drive;
        }
        throw new InvalidSessionPropertyException(DRIVE_SESSION_PROPERTY_NAME);
    }
    public void setDrive(Drive drive) {
        this.drive = drive;
    }

}
