package com.oaktwister.domain.seedwork;

import java.util.UUID;

public abstract class Entity {

    private UUID id;

    public Entity(UUID id) {
        this.id = id;
    }

    public Entity() {
        this(null);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isTransient() {
        return id == null;
    }

    public boolean equals(Entity entity) {
        return entity.getId() == getId();
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Entity entity) {
            return equals(entity);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(isTransient()) {
            return 0;
        }
        return id.hashCode();
    }

    @Override
    public String toString() {
        UUID id = getId();
        String className = getClass().getSimpleName();
        return String.format("(%s: %s)", className, id);
    }

}
