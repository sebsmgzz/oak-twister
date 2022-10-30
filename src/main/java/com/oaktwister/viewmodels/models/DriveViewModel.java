package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.drives.Drive;
import com.oaktwister.models.drives.Version;
import com.oaktwister.services.configs.SessionSettings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.UUID;

public class DriveViewModel {

    private final Drive drive;
    private final ViewModelFactory viewModelFactory;

    private final SimpleStringProperty pathProperty;

    public DriveViewModel(Drive drive, ViewModelFactory viewModelFactory) {
        this.drive = drive;
        this.viewModelFactory = viewModelFactory;
        pathProperty = new SimpleStringProperty(drive.getPath());
    }

    public Drive getDrive() {
        return drive;
    }

    public ReadOnlyStringProperty pathProperty() {
        return pathProperty;
    }

}
