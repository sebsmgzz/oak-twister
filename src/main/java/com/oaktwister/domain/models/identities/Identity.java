package com.oaktwister.domain.models.identities;

import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.domain.seedwork.Aggregate;
import com.oaktwister.domain.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Identity extends Entity implements Aggregate {

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
