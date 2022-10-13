package com.oaktwister.models;

import java.util.UUID;

public class Platform {

    private UUID id;
    private String name;
    private String image;
    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Platform(UUID id, String name, String image, String url) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Platform (" + id + ", " + name + ", " + url + ")";
    }

}
