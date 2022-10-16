package com.oaktwister.models.aggregators.drives;

public class Drive {

    private String path;
    private DataSize capacity;
    private DataSize space;
    private DriveProps props;

    public Drive(String path, DataSize capacity, DataSize space, DriveProps props) {
        this.path = path;
        this.capacity = capacity;
        this.space = space;
        this.props = props;
    }

    public Drive(String path, long capacityBytes, long spaceBytes, DriveProps props) {
        this(path, new DataSize(capacityBytes), new DataSize(spaceBytes), props);
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

    public boolean isPersistenceCapable() {
        return props.getId() != null;
    }

    public DriveProps props() {
        return props;
    }

}
