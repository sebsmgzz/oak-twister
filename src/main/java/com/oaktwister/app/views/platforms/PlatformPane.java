package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
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

    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    @FXML private Label identifierLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;

    public PlatformPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
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
    }

    public ObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }
    public UUID getIdentifier() {
        return identifierProperty().get();
    }
    public void setIdentifier(UUID value) {
        identifierProperty.set(value);
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    public Image getImage() {
        return imageProperty().get();
    }
    public void setImage(Image value) {
        imageProperty().set(value);
    }

    public StringProperty nameProperty() {
        return nameLabel.textProperty();
    }
    public String getName() {
        return nameProperty().get();
    }
    public void setName(String value) {
        nameProperty().set(value);
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty.get();
    }
    public void setCreatedAt(LocalDateTime value) {
        createdAtProperty().set(value);
    }

}
