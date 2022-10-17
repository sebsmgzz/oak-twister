package com.oaktwister.models.aggregators;

import com.oaktwister.models.props.ClaimMap;
import com.oaktwister.models.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Platform extends Entity {

    private String name;
    private UUID imageId;
    private String url;
    private LocalDateTime createdAt;
    private ClaimMap claims;

    public Platform(UUID id, String name, UUID imageId, String url, LocalDateTime createdAt) {
        super(id);
        this.name = name;
        this.imageId = imageId;
        this.url = url;
        claims = new ClaimMap();
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

    public ClaimMap getClaims() {
        return claims;
    }

    public void setClaims(ClaimMap claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "Platform (" + getId() + ", " + name + ", " + url + ")";
    }

}
