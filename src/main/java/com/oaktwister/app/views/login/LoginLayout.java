package com.oaktwister.app.views.login;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.DriveViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Login.LAYOUT)
public class LoginLayout extends AnchorPane implements Initializable {

    // UI
    @FXML private Hyperlink websiteHyperlink;
    @FXML private Hyperlink documentationHyperlink;
    @FXML private Hyperlink repositoryHyperlink;
    @FXML private DrivesComboBox drivesComboBox;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button loginButton;
    @FXML private Hyperlink newDriveHyperlink;

    public LoginLayout() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public DrivesComboBox drives() {
        return drivesComboBox;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onWebsiteLinkActionProperty() {
        return websiteHyperlink.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnWebsiteLinkAction() {
        return onWebsiteLinkActionProperty().get();
    }
    public void setOnWebsiteLinkAction(EventHandler<ActionEvent> value) {
        onWebsiteLinkActionProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onDocumentationLinkActionProperty() {
        return documentationHyperlink.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnDocumentationLinkAction() {
        return onDocumentationLinkActionProperty().get();
    }
    public void setOnDocumentationLink(EventHandler<ActionEvent> value) {
        onDocumentationLinkActionProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onRepositoryLinkActionProperty() {
        return repositoryHyperlink.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnRepositoryLinkAction() {
        return onRepositoryLinkActionProperty().get();
    }
    public void setOnRepositoryLinkAction(EventHandler<ActionEvent> value) {
        onRepositoryLinkActionProperty().set(value);
    }

    public StringProperty usernameProperty() {
        return usernameTextField.textProperty();
    }
    public String getUsername() {
        return usernameProperty().get();
    }
    public void setUsername(String value) {
        usernameProperty().set(value);
    }

    public StringProperty passwordProperty() {
        return passwordTextField.textProperty();
    }
    public String getPassword() {
        return passwordProperty().get();
    }
    public void setPassword(String value) {
        passwordProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onLoginActionProperty() {
        return loginButton.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnLoginAction() {
        return onLoginActionProperty().get();
    }
    public void setOnLoginAction(EventHandler<ActionEvent> value) {
        onLoginActionProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onNewDriveLinkActionProperty() {
        return newDriveHyperlink.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnNewDriveLinkAction() {
        return onNewDriveLinkActionProperty().get();
    }
    public void setOnNewDriveLinkAction(EventHandler<ActionEvent> value) {
        onNewDriveLinkActionProperty().set(value);
    }

}
