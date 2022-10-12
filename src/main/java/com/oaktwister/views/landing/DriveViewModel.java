package com.oaktwister.views.landing;

import com.oaktwister.models.Drive;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DriveViewModel {

    private final Drive driver;
    private final SimpleStringProperty name;
    private final SimpleStringProperty path;
    private final SimpleStringProperty fileSystem;
    private final SimpleDoubleProperty capacity;
    private final SimpleDoubleProperty space;

    public DriveViewModel(Drive drive) {
        this.driver = drive;
        name = new SimpleStringProperty(drive.getName());
        name.addListener((observable, oldValue, newValue) -> driver.setName(newValue));
        path = new SimpleStringProperty(drive.getPath());
        path.addListener((observable, oldValue, newValue) -> driver.setPath(newValue));
        fileSystem = new SimpleStringProperty(driver.getFileSystem());
        fileSystem.addListener((observable, oldValue, newValue) -> driver.setFileSystem(newValue));
        capacity = new SimpleDoubleProperty(driver.getCapacity());
        capacity.addListener((observable, oldValue, newValue) -> driver.setCapacity(newValue.doubleValue()));
        space = new SimpleDoubleProperty(driver.getSpace());
        space.addListener((observable, oldValue, newValue) -> driver.setSpace(newValue.doubleValue()));
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public SimpleStringProperty fileSystemProperty() {
        return fileSystem;
    }

    public SimpleDoubleProperty capacityProperty() {
        return capacity;
    }

    public SimpleDoubleProperty spaceProperty() {
        return space;
    }

}
