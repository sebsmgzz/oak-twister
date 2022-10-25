package com.oaktwister.views.laterals;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Laterals.IMAGE_BUTTON_BOX_VIEW)
public class ImageButtonBox extends HBox implements Initializable {

    private final ViewMediator viewMediator;

    @FXML private Button button;
    @FXML private ImageView imageView;

    public ImageButtonBox(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public StringProperty textProperty() {
        return button.textProperty();
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public void setImage(String imageUrl) {
        imageProperty().set(new Image(imageUrl));
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return button.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }

    public void setOnAction(EventHandler<ActionEvent> consumer) {
        onActionProperty().set(consumer);
    }

}
