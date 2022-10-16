package com.oaktwister.models.claims;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;

public class ClaimMap implements Iterable<Claim<?>> {

    private HashMap<String, Claim<?>> claims = new HashMap<>();

    public Claim<?> get(String name) {
        return claims.get(name);
    }

    public void add(Claim<?> claim) {
        claims.put(claim.getName(), claim);
    }

    public Claim<?> remove(String name) {
        return claims.remove(name);
    }

    public boolean contains(String name) {
        return claims.containsKey(name);
    }

    @NotNull
    @Override
    public Iterator<Claim<?>> iterator() {
        return claims.values().iterator();
    }

}
