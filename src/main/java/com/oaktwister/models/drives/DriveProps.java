package com.oaktwister.models.drives;

import java.util.UUID;

public class DriveProps {

    private UUID id;
    private Version version;

    public DriveProps(UUID id, Version version) {
        this.id = id;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
