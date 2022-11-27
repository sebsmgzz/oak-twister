package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Platforms.PANE)
public class PlatformPane extends AnchorPane implements Initializable {

    // UI
    @FXML private Label identifierLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;


    // Properties
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<PlatformViewModel> platformProperty;

    public PlatformPane() {
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        platformProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(LocalDateTimeUtil.toDefault(newValue));
        });
        platformProperty.addListener((observer, oldValue, newValue) -> {
            identifierProperty.bind(newValue.idProperty());
            imageView.imageProperty().bind(newValue.imageProperty());
            nameLabel.textProperty().bind(newValue.nameProperty());
            createdAtProperty.bind(newValue.createdAtProperty());
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

    public ReadOnlyObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }
    public UUID getIdentifier() {
        return identifierProperty().get();
    }

    public ReadOnlyObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    public Image getImage() {
        return imageProperty().get();
    }

    public ReadOnlyStringProperty nameProperty() {
        return nameLabel.textProperty();
    }
    public String getName() {
        return nameProperty().get();
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty.get();
    }

}
