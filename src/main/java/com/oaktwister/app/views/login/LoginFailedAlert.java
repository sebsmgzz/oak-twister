package com.oaktwister.app.views.login;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;

import com.oaktwister.app.views.DialogResult;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Login.FAILED_ALERT)
public class LoginFailedAlert extends AnchorPane implements Initializable {

    @FXML private ImageView iconImageView;
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;
    @FXML private Button okayButton;

    private final SimpleObjectProperty<DialogResult> resultProperty;
    private final SimpleObjectProperty<Stage> stageProperty;

    public LoginFailedAlert() {
        resultProperty = new SimpleObjectProperty<>();
        stageProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okayButton.setOnAction(event -> {
            resultProperty.set(DialogResult.OKAY);
            Stage stage = stageProperty.get();
            if(stage != null) {
                stage.close();
            }
        });
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }

    public SimpleObjectProperty<Stage> stageProperty() {
        return stageProperty;
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

}
