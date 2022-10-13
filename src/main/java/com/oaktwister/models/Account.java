package com.oaktwister.models;

import com.oaktwister.models.claims.Claim;

import java.util.*;

public class Account {

    private final Map<String, Claim<?>> claims;
    private final UUID platformId;
    private UUID identityId;

    public Account(UUID platformId) {
        this.platformId = platformId;
        claims = new HashMap<>();
    }

    public UUID getPlatformId() {
        return platformId;
    }

    public UUID getIdentityId() {
        return identityId;
    }

    public void assignIdentity(Identity identity) {
        this.identityId = identity.getId();
    }

    public void assignIdentity(UUID identityId) {
        this.identityId = identityId;
    }

    public void removeIdentity() {
        this.identityId = null;
    }

    public boolean hasIdentity() {
        return identityId != null;
    }

    public void addClaim(Claim<?> claim) {
        claims.put(claim.getName(), claim);
    }

    public void removeClaim(Claim<?> claim) {
        claims.remove(claim.getName());
    }

}
