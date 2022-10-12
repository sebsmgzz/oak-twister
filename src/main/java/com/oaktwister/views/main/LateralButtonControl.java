package com.oaktwister.views.main;

import com.oaktwister.services.Resources;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LateralButtonControl extends AnchorPane {

    @FXML
    private Button button;

    @FXML
    private ImageView imageView;

    public void setText(String value) {
        textProperty().set(value);
    }

    public String getText() {
        return textProperty().get();
    }

    public void setOnAction(EventHandler<ActionEvent> value) {
        onActionProperty().set(value);
    }

    public EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }

    public void setImage(Image value) {
        imageProperty().set(value);
    }

    public void setImage(String location) {
        InputStream stream = LateralButtonControl.class.getResourceAsStream(location);
        setImage(new Image(stream));
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public LateralButtonControl() throws IOException {
        super();
        URL resourceUrl = LateralButtonControl.class.getResource(Resources.Views.Main.LATERAL_BUTTON);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public StringProperty textProperty() {
        return button.textProperty();
    }

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return button.onActionProperty();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

}
