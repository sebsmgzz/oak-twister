package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.drives.Drive;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class DriveViewModel {

    private final Drive drive;

    private final SimpleStringProperty pathProperty;

    public DriveViewModel(Drive drive, ViewModelFactory viewModelFactory) {
        this.drive = drive;
        pathProperty = new SimpleStringProperty(drive.getPath());
    }

    public Drive getDrive() {
        return drive;
    }

    public ReadOnlyStringProperty pathProperty() {
        return pathProperty;
    }

}
