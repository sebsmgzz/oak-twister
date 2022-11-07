package com.oaktwister.domain.models;

import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.domain.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account extends Entity {

    private UUID platformId;
    private UUID identityId;
    private LocalDateTime createdAt;
    private GrantMap claims;

    public Account(UUID id, UUID platformId, UUID identityId, LocalDateTime createdAt) {
        super(id);
        this.platformId = platformId;
        this.identityId = identityId;
        this.createdAt = createdAt;
        claims = new GrantMap();
    }

    public Account(UUID platformId) {
        this(null, platformId, null, LocalDateTime.now());
    }

    public UUID getPlatformId() {
        return platformId;
    }

    public void setPlatformId(UUID platformId) {
        this.platformId = platformId;
    }

    public UUID getIdentityId() {
        return identityId;
    }

    public void setIdentityId(UUID identityId) {
        this.identityId = identityId;
    }

    public void assignIdentity(UUID identityId) {
        this.identityId = identityId;
    }

    public boolean hasIdentity() {
        return identityId != null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public GrantMap getGrants() {
        return claims;
    }

    public void setGrants(GrantMap claims) {
        this.claims = claims;
    }

}
