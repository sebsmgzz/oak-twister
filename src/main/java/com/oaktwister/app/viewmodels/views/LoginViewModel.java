package com.oaktwister.app.viewmodels.views;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.services.configs.Environment;
import com.oaktwister.domain.models.drives.Drive;
import com.oaktwister.domain.models.drives.MetaDrive;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.domain.services.drives.DriveLoader;
import com.oaktwister.app.viewmodels.models.DriveViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.awt.Desktop;

public class LoginViewModel {

    private final ViewModelFactory viewModelFactory;
    private final Session session;
    private final DriveLoader driveLoader;
    private final Environment environment;

    private final SimpleStringProperty usernameProperty;
    private final SimpleStringProperty passwordProperty;
    private final SimpleListProperty<DriveViewModel> drivesProperty;
    private final SimpleObjectProperty<DriveViewModel> selectedDriveProperty;
    private final SimpleStringProperty loginErrorMessageProperty;

    public LoginViewModel(ViewModelFactory viewModelFactory, Environment environment,
                          Session session, DriveLoader driveLoader) {
        this.viewModelFactory = viewModelFactory;
        this.environment = environment;
        this.session = session;
        this.driveLoader = driveLoader;
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        drivesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        selectedDriveProperty = new SimpleObjectProperty<>();
        loginErrorMessageProperty = new SimpleStringProperty();
    }

    public void loadDrives() {
        drivesProperty.clear();
        boolean isDev = environment.isDevelopment();
        List<Drive> drives = driveLoader.listAllDrives(isDev);
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
        MetaDrive metaDrive = driveLoader.loadMeta(drive);

        String username = usernameProperty.get();
        String password = passwordProperty.get();
        // TODO: Try to get the encryption key with the username and password

        // TODO: Set the encryption key in the SessionSettings
        session.setMetaDrive(metaDrive);
        session.setDrive(drive);
        return true;
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

    public StringProperty usernameProperty() {
        return usernameProperty;
    }
    public String getUsername() {
        return usernameProperty().get();
    }
    public void setUsername(String value) {
        usernameProperty().set(value);
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }
    public String getPassword() {
        return passwordProperty().get();
    }
    public void setPassword(String value) {
        passwordProperty().set(value);
    }

    public ReadOnlyListProperty<DriveViewModel> drivesProperty() {
        return drivesProperty;
    }
    public ObservableList<DriveViewModel> getDrives() {
        return drivesProperty().get();
    }
    public boolean addDrive(DriveViewModel drive) {
        return getDrives().add(drive);
    }
    public boolean addDrives(DriveViewModel... drives) {
        return getDrives().addAll(drives);
    }
    public boolean removeDrive(DriveViewModel drive) {
        return getDrives().remove(drive);
    }
    public boolean removeDrives(DriveViewModel... drives) {
        return getDrives().remove(drives);
    }
    public void clear() {
        getDrives().clear();
    }

    public ObjectProperty<DriveViewModel> selectedDriveProperty() {
        return selectedDriveProperty;
    }
    public DriveViewModel getSelectedDrive() {
        return selectedDriveProperty().get();
    }
    public void setSelectedDrive(DriveViewModel value) {
        selectedDriveProperty().set(value);
    }

    public ReadOnlyStringProperty loginErrorMessageProperty() {
        return loginErrorMessageProperty;
    }
    public String getLoginErrorMessage() {
        return loginErrorMessageProperty().get();
    }

}
