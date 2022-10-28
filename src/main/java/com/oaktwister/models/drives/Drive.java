package com.oaktwister.models.drives;

public class Drive {

    private String path;
    private DataSize capacity;
    private DataSize space;
    private DriveMetaData props;

    public Drive(String path, DataSize capacity, DataSize space, DriveMetaData props) {
        this.path = path;
        this.capacity = capacity;
        this.space = space;
        this.props = props;
    }

    public Drive(String path, long capacityBytes, long spaceBytes, DriveMetaData props) {
        this(path, new DataSize(capacityBytes), new DataSize(spaceBytes), props);
    }

    public String getPath() {
        return path;
    }

    public DataSize getCapacity() {
        return capacity;
    }

    public DataSize getSpace() {
        return space;
    }

    public boolean isPersistenceCapable() {
        return props.getId() != null;
    }

    public DriveMetaData props() {
        return props;
    }

    public void encrypt(EncryptionKey encryptionKey) {
        // TODO: Encrypt drive
    }

}
