package com.oaktwister.models.aggregators;

import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.models.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account extends Entity {

    private UUID platformId;
    private UUID identityId;
    private LocalDateTime createdAt;
    private final ClaimMap claims;

    public Account(UUID id, UUID platformId, UUID identityId, LocalDateTime createdAt) {
        super(id);
        this.platformId = platformId;
        this.identityId = identityId;
        this.createdAt = createdAt;
        claims = new ClaimMap();
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

    public ClaimMap claims() {
        return claims;
    }

}
