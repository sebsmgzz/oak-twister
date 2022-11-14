package com.oaktwister.app.views.widgets.dialogs;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.Dialogs.ALERT)
public class Alert extends AnchorPane implements Initializable, DialogControl {

    private final SimpleObjectProperty<AlertType> alertTypeProperty;

    @FXML private DialogFrame dialogFrame;
    @FXML private Label messageLabel;

    public Alert() {
        alertTypeProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertTypeProperty.addListener(this::onAlertTypeChanged);
    }

    public void showAndWait(Stage stage) {
        stage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        dialogFrame.showAndWait(stage);
    }

    private void onAlertTypeChanged(ObservableValue<? extends AlertType> observable, AlertType oldValue, AlertType newValue) {
        dialogFrame.setTitle(newValue.getName());
        if (newValue == AlertType.CONFIRM) {
            // TODO: Load image from ImageResources.CONFIRM_ICON
            dialogFrame.clearButtons();
            dialogFrame.addButtons(
                    DialogButton.fromResult(DialogResult.YES),
                    DialogButton.fromResult(DialogResult.NO));
        } else if (newValue == AlertType.INFO) {
            // TODO: Load image from ImageResources.INFO_ICON
            dialogFrame.clearButtons();
            dialogFrame.addButtons(DialogButton.fromResult(DialogResult.OKAY));
        } else if (newValue == AlertType.WARNING) {
            // TODO: Load image from ImageResources.WARNING_ICON
            dialogFrame.clearButtons();
            dialogFrame.addButtons(DialogButton.fromResult(DialogResult.OKAY));
        } else if (newValue == AlertType.ERROR) {
            // TODO: Load image from ImageResources.ERROR_ICON
            dialogFrame.clearButtons();
            dialogFrame.addButtons(DialogButton.fromResult(DialogResult.OKAY));
        } else {
        }
    }

    public ObjectProperty<AlertType> alertTypeProperty() {
        return alertTypeProperty;
    }
    public AlertType getAlertType() {
        return alertTypeProperty().get();
    }
    public void setAlertType(AlertType value) {
        alertTypeProperty().set(value);
    }

    public StringProperty messageProperty() {
        return messageLabel.textProperty();
    }
    public String getMessage() {
        return messageProperty().get();
    }
    public void setMessage(String value) {
        messageProperty().set(value);
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return dialogFrame.resultProperty();
    }
    public DialogResult getResult() {
        return resultProperty().get();
    }

}
