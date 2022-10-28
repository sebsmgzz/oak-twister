package com.oaktwister.viewmodels.roots;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.drives.Drive;
import com.oaktwister.services.repos.DriveRepo;
import com.oaktwister.viewmodels.models.DriveViewModel;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class LandingViewModel {

    private final ViewModelFactory viewModelFactory;
    private final DriveRepo driveRepo;
    private final SimpleListProperty<DriveViewModel> drivesProperty;
    private final SimpleObjectProperty<DriveViewModel> selectedDriveProperty;

    public LandingViewModel(ViewModelFactory viewModelFactory, DriveRepo driveRepo) {
        this.viewModelFactory = viewModelFactory;
        this.driveRepo = driveRepo;
        drivesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        selectedDriveProperty = new SimpleObjectProperty<>();
    }

    public ReadOnlyListProperty<DriveViewModel> drivesProperty() {
        return drivesProperty;
    }

    public SimpleObjectProperty<DriveViewModel> selectedDriveProperty() {
        return selectedDriveProperty;
    }

    public void loadDrives() {
        drivesProperty.clear();
        List<Drive> drives = driveRepo.getAllDrives();
        for(Drive drive : drives) {
            DriveViewModel driveViewModel = viewModelFactory.getDriveViewModel();
            driveViewModel.setDrive(drive);
            drivesProperty.add(driveViewModel);
        }
    }

    public void loadContext() throws IllegalArgumentException {
        if(selectedDriveProperty.isNull().get()) {
            throw new IllegalArgumentException(
                "There is no current selected drive. " +
                "Please select a persistence capable drive first.");
        }
        selectedDriveProperty.get().attachToContext();
    }

}
