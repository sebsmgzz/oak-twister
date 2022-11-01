package com.oaktwister.views.dialogs;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Dialogs.FAILED_LOGIN)
public class LoginFailedAlert extends AnchorPane implements Initializable {

    @FXML private ImageView iconImageView;
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;
    @FXML private Button okayButton;

    private final SimpleObjectProperty<DialogResult> resultProperty;

    public LoginFailedAlert() {
        resultProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okayButton.setOnAction(event -> {
            resultProperty.set(DialogResult.OKAY);
        });
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public StringProperty messageProperty() {
        return messageLabel.textProperty();
    }

    public ObjectProperty<Image> iconProperty() {
        return iconImageView.imageProperty();
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }

}
