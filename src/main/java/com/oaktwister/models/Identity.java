package com.oaktwister.models;

import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.models.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Identity extends Entity {

    private LocalDateTime createdAt;
    private GrantMap grants;
    private String name;

    public Identity(UUID id, LocalDateTime createdAt) {
        super(id);
        this.createdAt = createdAt;
        grants = new GrantMap();
    }

    public Identity() {
        this(null, LocalDateTime.now());
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public GrantMap getGrantMap() {
        return grants;
    }

    public void setGrants(GrantMap grants) {
        this.grants = grants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
