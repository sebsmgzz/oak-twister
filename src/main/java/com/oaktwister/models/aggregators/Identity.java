package com.oaktwister.models.aggregators;

import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.models.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Identity extends Entity {

    private LocalDateTime createdAt;
    private final ClaimMap claims;

    public Identity(UUID id, LocalDateTime createdAt) {
        super(id);
        this.createdAt = createdAt;
        claims = new ClaimMap();
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

    public ClaimMap claims() {
        return claims;
    }

}
