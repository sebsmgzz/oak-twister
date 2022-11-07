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

}
