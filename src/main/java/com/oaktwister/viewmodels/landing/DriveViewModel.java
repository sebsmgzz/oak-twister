package com.oaktwister.viewmodels.landing;

import com.oaktwister.models.Drive;
import javafx.beans.property.*;

import java.util.UUID;

public class DriveViewModel {

    private final Drive driver;
    private final ObjectProperty<UUID> uuid;
    private final SimpleStringProperty path;
    private final ReadOnlyStringProperty capacity;
    private final ReadOnlyStringProperty space;

    public DriveViewModel(Drive drive) {
        this.driver = drive;
        uuid = new SimpleObjectProperty<UUID>(drive.getId());
        uuid.addListener((observable, oldValue, newValue) -> driver.setId(newValue));
        path = new SimpleStringProperty(drive.getPath());
        path.addListener((observable, oldValue, newValue) -> driver.setPath(newValue));
        capacity = new SimpleStringProperty(driver.getCapacity().toString());
        space = new SimpleStringProperty(driver.getSpace().toString());
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
