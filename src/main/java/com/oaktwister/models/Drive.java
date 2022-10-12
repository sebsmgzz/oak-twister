package com.oaktwister.models;

public class Drive {

    private String name;
    private String path;
    private String fileSystem;
    private double capacity;
    private double space;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        path = value;
    }

    public String getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(String value) {
        fileSystem = value;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double value) {
        capacity = value;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double value) {
        space = value;
    }

    public Drive(String name, String path, String fileSystem, double capacity, double space) {
        this.name = name;
        this.path = path;
        this.fileSystem = fileSystem;
        this.capacity = capacity;
        this.space = space;
    }

}
