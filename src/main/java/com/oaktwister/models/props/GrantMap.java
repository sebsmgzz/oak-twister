package com.oaktwister.models.props;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;

public class GrantMap implements Iterable<Grant<?>> {

    private HashMap<String, Grant<?>> claims = new HashMap<>();

    public Grant<?> get(String name) {
        return claims.get(name);
    }

    public void add(Grant<?> grant) {
        claims.put(grant.getName(), grant);
    }

    public Grant<?> remove(String name) {
        return claims.remove(name);
    }

    public boolean contains(String name) {
        return claims.containsKey(name);
    }

    @NotNull
    @Override
    public Iterator<Grant<?>> iterator() {
        return claims.values().iterator();
    }

}
