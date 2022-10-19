package com.oaktwister.models.props;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ClaimMap implements Iterable<Claim> {

    private HashMap<String, Claim> claims = new HashMap<>();

    public Claim get(String name) {
        return claims.get(name);
    }

    public void add(Claim claim) {
        claims.put(claim.getName(), claim);
    }

    public Claim remove(String name) {
        return claims.remove(name);
    }

    public Claim remove(Claim claim) {
        return remove(claim.getName());
    }

    public boolean contains(String name) {
        return claims.containsKey(name);
    }

    public int size() {
        return claims.size();
    }

    public Collection<Claim> claims() {
        return claims.values();
    }

    @NotNull
    @Override
    public Iterator<Claim> iterator() {
        return claims.values().iterator();
    }

}
