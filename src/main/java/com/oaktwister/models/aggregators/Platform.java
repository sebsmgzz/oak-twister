package com.oaktwister.models.aggregators;

import com.oaktwister.models.claims.ClaimDefinitionMap;
import com.oaktwister.models.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Platform extends Entity {

    private String name;
    private UUID imageId;
    private String url;
    private LocalDateTime createdAt;
    private final ClaimDefinitionMap claimDefinitions;

    public Platform(UUID id, String name, UUID imageId, String url, LocalDateTime createdAt) {
        super(id);
        this.name = name;
        this.imageId = imageId;
        this.url = url;
        claimDefinitions = new ClaimDefinitionMap();
    }

    public Platform(String name, UUID imageId, String url) {
        this(null, name, imageId, url, LocalDateTime.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ClaimDefinitionMap claimDefinitions() {
        return claimDefinitions;
    }

    @Override
    public String toString() {
        return "Platform (" + getId() + ", " + name + ", " + url + ")";
    }

}
