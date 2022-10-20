package com.oaktwister.models.aggregators.grants;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class GrantMap implements Iterable<Grant<?>> {

    private HashMap<String, Grant<?>> grants = new HashMap<>();

    public Grant<?> get(String name) {
        return grants.get(name);
    }

    public void add(Grant<?> grant) {
        grants.put(grant.getName(), grant);
    }

    public Grant<?> remove(String name) {
        return grants.remove(name);
    }

    public Grant<?> remove(Grant<?> grant) {
        return remove(grant.getName());
    }

    public boolean contains(String name) {
        return grants.containsKey(name);
    }

    public Collection<Grant<?>> grants() {
        return grants.values();
    }

    @NotNull
    @Override
    public Iterator<Grant<?>> iterator() {
        return grants.values().iterator();
    }

}
