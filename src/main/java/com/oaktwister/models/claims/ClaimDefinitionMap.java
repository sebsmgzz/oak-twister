package com.oaktwister.models.claims;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;

public class ClaimDefinitionMap implements Iterable<ClaimDefinition> {

    private HashMap<String, ClaimDefinition> claims = new HashMap<>();

    public ClaimDefinition get(String name) {
        return claims.get(name);
    }

    public void add(ClaimDefinition claimDefinition) {
        claims.put(claimDefinition.getName(), claimDefinition);
    }

    public ClaimDefinition remove(String name) {
        return claims.remove(name);
    }

    public boolean contains(String name) {
        return claims.containsKey(name);
    }

    @NotNull
    @Override
    public Iterator<ClaimDefinition> iterator() {
        return claims.values().iterator();
    }

}
