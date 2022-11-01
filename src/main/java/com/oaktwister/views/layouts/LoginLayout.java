package com.oaktwister.views.layouts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.DriveViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Windows.LOGIN)
public class LoginLayout extends AnchorPane implements Initializable {

    @FXML private Hyperlink websiteHyperlink;
    @FXML private Hyperlink documentationHyperlink;
    @FXML private Hyperlink repositoryHyperlink;
    @FXML private ComboBox<DriveViewModel> driveComboBox;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button loginButton;
    @FXML private Hyperlink newDriveHyperlink;

    public LoginLayout() {
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public ObjectProperty<EventHandler<ActionEvent>> onWebsiteLinkActionProperty() {
        return websiteHyperlink.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onDocumentationLinkActionProperty() {
        return documentationHyperlink.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onRepositoryLinkActionProperty() {
        return repositoryHyperlink.onActionProperty();
    }

    public ObjectProperty<ListCell<DriveViewModel>> driveButtonCellProperty() {
        return driveComboBox.buttonCellProperty();
    }

    public ObjectProperty<Callback<ListView<DriveViewModel>, ListCell<DriveViewModel>>> driveCellFactoryProperty() {
        return driveComboBox.cellFactoryProperty();
    }

    public ObjectProperty<ObservableList<DriveViewModel>> drivesProperty() {
        return driveComboBox.itemsProperty();
    }

    public ObjectProperty<DriveViewModel> driveProperty() {
        return driveComboBox.valueProperty();
    }

    public ObjectProperty<EventHandler<Event>> onShowingDrivesProperty() {
        return driveComboBox.onShowingProperty();
    }

    public StringProperty usernameProperty() {
        return usernameTextField.textProperty();
    }

    public StringProperty passwordProperty() {
        return passwordTextField.textProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onLoginActionProperty() {
        return loginButton.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onNewDriveLinkActionProperty() {
        return newDriveHyperlink.onActionProperty();
    }

}
