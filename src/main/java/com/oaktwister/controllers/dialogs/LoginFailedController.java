package com.oaktwister.controllers.dialogs;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Dialogs.FAILED_LOGIN)
public class LoginFailedController implements Initializable {

    private final Stage stage;

    @FXML private ImageView iconImageView;
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;
    @FXML private Button okayButton;

    public LoginFailedController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okayButton.setOnAction(event -> {
            stage.close();
        });
    }

    public ObjectProperty<Image> iconProperty() {
        return iconImageView.imageProperty();
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public StringProperty messageProperty() {
        return messageLabel.textProperty();
    }

}
