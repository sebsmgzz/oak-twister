package com.oaktwister.domain.models.drives;

import java.util.UUID;

public class DriveMeta {

    private final Version version;
    private final String username;
    private final UUID userId;

    public DriveMeta(Version version, String username, UUID userId) {
        this.version = version;
        this.username = username;
        this.userId = userId;
    }

    public Version getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserId() {
        return userId;
    }

}
