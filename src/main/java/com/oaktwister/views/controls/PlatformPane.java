package com.oaktwister.views.controls;

import com.oaktwister.services.Resources;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class PlatformPane extends VBox implements Initializable {

    @FXML private Label identifier;
    @FXML private ImageView image;
    @FXML private Label name;

    private final SimpleObjectProperty<UUID> id;

    public PlatformPane() throws IOException {
        super();
        id = new SimpleObjectProperty<>(UUID.randomUUID());
        URL resourceUrl = PlatformPane.class.getResource(Resources.Views.Controls.PLATFORM_PANE);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setControllerFactory(aClass -> this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifier.textProperty().bindBidirectional(id, new UUIDStringConverter());
    }

    public ObjectProperty<UUID> identifierProperty() {
        return id;
    }

    public UUID getIdentifier() {
        return identifierProperty().get();
    }

    public void setIdentifier(UUID identifier) {
        identifierProperty().set(identifier);
    }

    public ObjectProperty<Image> imageProperty() {
        return image.imageProperty();
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public StringProperty nameProperty() {
        return name.textProperty();
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

}
