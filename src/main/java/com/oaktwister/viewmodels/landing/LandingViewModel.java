package com.oaktwister.viewmodels.landing;

import com.oaktwister.models.Drive;
import com.oaktwister.services.DriveFactory;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.List;

public class LandingViewModel {

    private final DriveFactory driveFactory;
    private final SimpleListProperty<DriveViewModel> drives;

    public ObservableList<DriveViewModel> getDrives() {
        return drivesProperty().get();
    }

    public void setDrives(Collection<DriveViewModel> drives) {
        drivesProperty().setAll(drives);
    }

    public LandingViewModel(DriveFactory driveFactory) {
        this.driveFactory = driveFactory;
        drives = new SimpleListProperty<DriveViewModel>(FXCollections.observableArrayList());
    }

    public SimpleListProperty<DriveViewModel> drivesProperty() {
        return drives;
    }

    public void loadDrives() {
        drivesProperty().clear();
        List<Drive> drives = driveFactory.getAllDrives();
        for(Drive drive : drives) {
            DriveViewModel driveViewModel = new DriveViewModel(drive);
            this.drives.add(driveViewModel);
        }
    }

}
