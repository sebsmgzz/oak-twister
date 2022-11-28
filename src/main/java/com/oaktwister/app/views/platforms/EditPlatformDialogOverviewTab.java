package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG_OVERVIEW_TAB)
public class EditPlatformDialogOverviewTab extends GridPane implements Initializable {

    @FXML private TextField platformNameTextField;
    @FXML private TextField platformUrlTextField;
    @FXML private ImageView platformImageView;

    public EditPlatformDialogOverviewTab() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public StringProperty platformNameProperty() {
        return platformNameTextField.textProperty();
    }
    public String getPlatformName() {
        return platformNameProperty().get();
    }
    public void setPlatformName(String value) {
        platformNameProperty().set(value);
    }

    public StringProperty platformUrlProperty() {
        return platformUrlTextField.textProperty();
    }
    public String getPlatformUrl() {
        return platformUrlProperty().get();
    }
    public void setPlatformUlr(String value) {
        platformUrlProperty().set(value);
    }

    public ObjectProperty<Image> platformImageProperty() {
        return platformImageView.imageProperty();
    }
    public Image getPlatformImage() {
        return platformImageProperty().get();
    }
    public void setPlatformImage(Image value) {
        platformImageProperty().set(value);
    }

}
