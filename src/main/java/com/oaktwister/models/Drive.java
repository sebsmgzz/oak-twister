package com.oaktwister.models;

import java.util.UUID;

public class Drive {

    private UUID id;
    private String path;
    private DataSize capacity;
    private DataSize space;

    public UUID getId() {
        return id;
    }

    public void setId(UUID value) {
        id = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        path = value;
    }

    public DataSize getCapacity() {
        return capacity;
    }

    public void setCapacity(DataSize value) {
        capacity = value;
    }

    public DataSize getSpace() {
        return space;
    }

    public void setSpace(DataSize value) {
        space = value;
    }

    public Drive(UUID id, String path, DataSize capacity, DataSize space) {
        this.id = id;
        this.path = path;
        this.capacity = capacity;
        this.space = space;
    }

    public Drive(UUID id, String path, long capacity, long space) {
        this(id, path, new DataSize(capacity), new DataSize(space));
    }

    public boolean isPersistenceCapable() {
        return id != null;
    }

}
