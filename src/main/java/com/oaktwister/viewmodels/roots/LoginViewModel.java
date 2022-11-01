package com.oaktwister.viewmodels.roots;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.drives.Drive;
import com.oaktwister.models.drives.DriveMeta;
import com.oaktwister.services.configs.SessionSettings;
import com.oaktwister.services.drives.DriveLoader;
import com.oaktwister.viewmodels.models.DriveViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.awt.Desktop;

public class LoginViewModel {

    private final ViewModelFactory viewModelFactory;
    private final SessionSettings sessionSettings;
    private final DriveLoader driveLoader;

    private final SimpleStringProperty usernameProperty;
    private final SimpleStringProperty passwordProperty;
    private final SimpleListProperty<DriveViewModel> drivesProperty;
    private final SimpleObjectProperty<DriveViewModel> selectedDriveProperty;
    private final SimpleStringProperty loginErrorMessageProperty;

    public LoginViewModel(ViewModelFactory viewModelFactory,
                          SessionSettings sessionSettings, DriveLoader driveLoader) {
        this.viewModelFactory = viewModelFactory;
        this.sessionSettings = sessionSettings;
        this.driveLoader = driveLoader;
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        drivesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        selectedDriveProperty = new SimpleObjectProperty<>();
        loginErrorMessageProperty = new SimpleStringProperty();
    }

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public ReadOnlyListProperty<DriveViewModel> drivesProperty() {
        return drivesProperty;
    }

    public ObjectProperty<DriveViewModel> selectedDriveProperty() {
        return selectedDriveProperty;
    }

    public ReadOnlyStringProperty loginErrorMessageProperty() {
        return loginErrorMessageProperty;
    }

    public void loadDrives() {
        drivesProperty.clear();
        List<Drive> drives = driveLoader.listAllDrives();
        for (Drive drive : drives) {
            DriveViewModel driveViewModel = viewModelFactory.drive(drive);
            drivesProperty.add(driveViewModel);
        }
    }

    public boolean tryLogin() {

        // Try to get the selected drive
        DriveViewModel driveViewModel = selectedDriveProperty.get();
        if(driveViewModel == null) {
            loginErrorMessageProperty.set("No drive has been selected. " +
                "Please select an oak capable drive before logging in");
            return false;
        }
        Drive drive = driveViewModel.getDrive();
        DriveMeta driveMeta = driveLoader.loadMeta(drive);

        String username = usernameProperty.get();
        String password = passwordProperty.get();
        // TODO: Try to get the encryption key with the username and password

        // TODO: Set the encryption key in the SessionSettings
        sessionSettings.setMeta(driveMeta);
        sessionSettings.setDrive(drive);
        return false;
    }

    public void browse(String urlString) {
        try {
            URL url = new URL(urlString);
            URI uri = url.toURI();
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
