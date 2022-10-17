package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class PlatformPane extends VBox implements View {

    private final ViewHandler viewHandler;
    private final PlatformViewModel viewModel;

    @FXML private Label identifier;
    @FXML private ImageView image;
    @FXML private Label name;

    private final SimpleObjectProperty<UUID> id;

    public PlatformPane(ViewHandler viewHandler, PlatformViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        id = new SimpleObjectProperty<>(viewModel.getId());
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.PLATFORM_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.bindBidirectional(viewModel.idProperty());
        identifier.textProperty().bindBidirectional(id, new UUIDStringConverter());
        name.textProperty().bindBidirectional(viewModel.nameProperty());
        image.imageProperty().bindBidirectional(viewModel.imageProperty());
    }

    public PlatformViewModel getViewModel() {
        return viewModel;
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
