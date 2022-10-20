package com.oaktwister.viewmodels.roots;

import com.oaktwister.models.aggregators.drives.Drive;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.repos.DriveRepo;
import com.oaktwister.viewmodels.models.DriveViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.List;

public class LandingViewModel {

    private final Context context;
    private final DriveRepo driveRepo;
    private final SimpleListProperty<DriveViewModel> drives;
    private final SimpleObjectProperty<DriveViewModel> selectedDrive;

    public ObservableList<DriveViewModel> getDrives() {
        return drivesProperty().get();
    }

    public void setDrives(Collection<DriveViewModel> drives) {
        drivesProperty().setAll(drives);
    }

    public DriveViewModel getSelectedDrive() {
        return selectedDriveProperty().get();
    }

    public void setSelectedDrive(DriveViewModel selectedDrive) {
        selectedDriveProperty().set(selectedDrive);
    }

    public LandingViewModel(Context context, DriveRepo driveRepo) {
        this.context = context;
        this.driveRepo = driveRepo;
        drives = new SimpleListProperty<DriveViewModel>(FXCollections.observableArrayList());
        selectedDrive = new SimpleObjectProperty<DriveViewModel>();
    }

    public SimpleListProperty<DriveViewModel> drivesProperty() {
        return drives;
    }

    public SimpleObjectProperty<DriveViewModel> selectedDriveProperty() {
        return selectedDrive;
    }

    public void loadDrives() {
        drivesProperty().clear();
        List<Drive> drives = driveRepo.getAllDrives();
        for(Drive drive : drives) {
            DriveViewModel driveViewModel = new DriveViewModel(drive);
            drivesProperty().add(driveViewModel);
        }
    }

    public void loadContext() throws IllegalArgumentException {
        if(selectedDrive.get() == null) {
            throw new IllegalArgumentException(
                "There is no current selected drive. " +
                "Please select a persistence capable drive first.");
        }
        Drive drive = selectedDrive.get().getDrive();
        if(!drive.isPersistenceCapable()) {
            throw new IllegalArgumentException(
                "The current drive selection must be persistence capable. " +
                "Try selecting a different drive or formatting this drive first.");
        }
        context.setDrive(drive);
    }

}
