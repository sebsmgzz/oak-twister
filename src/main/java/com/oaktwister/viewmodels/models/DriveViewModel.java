package com.oaktwister.viewmodels.models;

import com.oaktwister.models.drives.DataSize;
import com.oaktwister.models.drives.Drive;
import com.oaktwister.services.config.Context;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.UUID;

public class DriveViewModel {

    private final Context context;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleStringProperty pathProperty;
    private final SimpleObjectProperty<DataSize> capacityProperty;
    private final SimpleObjectProperty<DataSize> spaceProperty;

    public DriveViewModel(Context context) {
        this.context = context;
        idProperty = new SimpleObjectProperty<>();
        pathProperty = new SimpleStringProperty();
        capacityProperty = new SimpleObjectProperty<>();
        spaceProperty = new SimpleObjectProperty<>();
    }

    public void setDrive(Drive drive) {
        UUID id = drive.props().getId();
        idProperty.set(id);
        String path = drive.getPath();
        pathProperty.set(path);
        DataSize capacity = drive.getCapacity();
        capacityProperty.set(capacity);
        DataSize space = drive.getSpace();
        spaceProperty.set(space);
    }

    public Drive getDrive() {
        String path = pathProperty.get();
        DataSize capacity = capacityProperty.get();
        DataSize space = spaceProperty.get();
        return new Drive(path, capacity, space, null);
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public ReadOnlyStringProperty pathPropertyProperty() {
        return pathProperty;
    }

    public ReadOnlyObjectProperty<DataSize> capacityProperty() {
        return capacityProperty;
    }

    public ReadOnlyObjectProperty<DataSize> spaceProperty() {
        return spaceProperty;
    }

    public void attachToContext() {
        Drive drive = getDrive();
        if(!drive.isPersistenceCapable()) {
            throw new IllegalArgumentException(
                    "The current drive selection must be persistence capable. " +
                            "Try selecting a different drive or formatting this drive first.");
        }
        context.setDrive(drive);
    }

}
