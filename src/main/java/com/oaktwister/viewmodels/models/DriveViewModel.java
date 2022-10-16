package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.drives.Drive;
import javafx.beans.property.*;

import java.util.UUID;

public class DriveViewModel {

    private final Drive drive;
    private final ObjectProperty<UUID> uuid;
    private final SimpleStringProperty path;
    private final ReadOnlyStringProperty capacity;
    private final ReadOnlyStringProperty space;

    public DriveViewModel(Drive drive) {
        this.drive = drive;
        if(drive.isPersistenceCapable()) {
            uuid = new SimpleObjectProperty<UUID>(drive.props().getId());
        } else {
            uuid = new SimpleObjectProperty<>(null);
        }
        uuid.addListener((observable, oldValue, newValue) -> this.drive.props().setId(newValue));
        path = new SimpleStringProperty(drive.getPath());
        path.addListener((observable, oldValue, newValue) -> this.drive.setPath(newValue));
        capacity = new SimpleStringProperty(this.drive.getCapacity().toString());
        space = new SimpleStringProperty(this.drive.getSpace().toString());
    }

    public Drive getDrive() {
        return drive;
    }

    public ObjectProperty<UUID> idProperty() {
        return uuid;
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public ReadOnlyStringProperty capacityProperty() {
        return capacity;
    }

    public ReadOnlyStringProperty spaceProperty() {
        return space;
    }

}
