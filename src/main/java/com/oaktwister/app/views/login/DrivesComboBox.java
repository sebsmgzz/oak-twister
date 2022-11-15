package com.oaktwister.app.views.login;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.DriveViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Login.DRIVES_COMBO_BOX)
public class DrivesComboBox extends AnchorPane implements Initializable {

    @FXML private ComboBox<DriveViewModel> comboBox;

    public DrivesComboBox() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setButtonCell(new DrivesComboBoxCell());
        comboBox.setCellFactory(listView -> new DrivesComboBoxCell());
    }

    public ObjectProperty<EventHandler<Event>> onShowingProperty() {
        return comboBox.onShowingProperty();
    }
    public EventHandler<Event> getOnShowing() {
        return onShowingProperty().get();
    }
    public void setOnShowing(EventHandler<Event> value) {
        onShowingProperty().set(value);
    }

    public ObjectProperty<DriveViewModel> selectedDriveProperty() {
        return comboBox.valueProperty();
    }
    public DriveViewModel getSelectedDrive() {
        return selectedDriveProperty().get();
    }
    public void setSelectedDrive(DriveViewModel value) {
        selectedDriveProperty().set(value);
    }

    public ReadOnlyObjectProperty<ObservableList<DriveViewModel>> drivesProperty() {
        return comboBox.itemsProperty();
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
    public void clearDrives() {
        getDrives().clear();
    }

}
