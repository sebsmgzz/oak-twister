package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class IdentityCell extends ListCell<IdentityViewModel> implements View {

    private final ViewHandler viewHandler;

    @FXML private Label identifier;
    @FXML private ImageView genderImage;
    @FXML private Label email;

    private final SimpleObjectProperty<UUID> id;

    public IdentityCell(ViewHandler viewHandler) {
        super();
        this.viewHandler = viewHandler;
        id = new SimpleObjectProperty<>(UUID.randomUUID());
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.IDENTITY_CELL;
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
        return genderImage.imageProperty();
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public StringProperty emailProperty() {
        return email.textProperty();
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public void setEmail(String email) {
        emailProperty().set(email);
    }

}
