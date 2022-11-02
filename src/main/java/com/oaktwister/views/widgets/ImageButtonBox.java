package com.oaktwister.views.widgets;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;

import com.oaktwister.utils.extensions.NodeUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.IMAGE_BUTTON_BOX)
public class ImageButtonBox extends HBox implements Initializable {

    @FXML private Button button;
    @FXML private ImageView imageView;

    public ImageButtonBox() {
        super();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setStyle("""
                -fx-background-radius: 0;
                -fx-background-color: transparent;
                """);
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                super.setStyle("""
                        -fx-background-radius: 0;
                        -fx-background-color: #E8CFB5;
                
                        -fx-border-color: #c12126;
                        -fx-cursor: hand;
                        -fx-border-width: 0 0 0 5px;
                        """);
            } else {
                super.setStyle("""
                        -fx-background-radius: 0;
                        -fx-background-color: transparent;
                
                        -fx-cursor: none;
                        -fx-border-width: 0;
                        """);
            }
        });
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
