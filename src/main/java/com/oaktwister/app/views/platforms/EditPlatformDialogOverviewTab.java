package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG_OVERVIEW_TAB)
public class EditPlatformDialogOverviewTab extends GridPane implements Initializable {

    // UI
    @FXML private TextField platformNameTextField;
    @FXML private TextField platformUrlTextField;
    @FXML private ImageView platformImageView;

    // Properties
    private final SimpleObjectProperty<PlatformViewModel> platformProperty;

    public EditPlatformDialogOverviewTab() {
        platformProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        platformProperty.addListener((observable, oldValue, newValue) -> {
            platformNameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            platformUrlTextField.textProperty().bindBidirectional(newValue.urlProperty());
            platformImageView.imageProperty().bindBidirectional(newValue.imageProperty());
        });
    }

    public ObjectProperty<PlatformViewModel> platformProperty() {
        return platformProperty;
    }
    public PlatformViewModel getPlatform() {
        return platformProperty().get();
    }
    public void setPlatform(PlatformViewModel value) {
        platformProperty().set(value);
    }

}
