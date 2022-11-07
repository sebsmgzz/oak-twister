package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.PlatformPaneEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import com.oaktwister.app.views.widgets.DeleteFrame;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
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

    private final SimpleObjectProperty<EventHandler<PlatformPaneEvent>> onMainActionProperty;
    private final SimpleObjectProperty<EventHandler<PlatformPaneEvent>> onDeleteActionProperty;
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    @FXML private DeleteFrame deleteFrame;
    @FXML private Label identifierLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;

    public PlatformPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onMainActionProperty = new SimpleObjectProperty<>();
        onDeleteActionProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(PlatformPane.class, this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(LocalDateTimeUtil.toDefault(newValue));
        });
        deleteFrame.onMainActionProperty().set(event -> {
            if (onMainActionProperty.isNotNull().get()) {
                PlatformPaneEvent platformPaneEvent = new PlatformPaneEvent(this);
                onMainActionProperty.get().handle(platformPaneEvent);
            }
        });
        deleteFrame.onDeleteActionProperty().set(event -> {
            if (onDeleteActionProperty.isNotNull().get()) {
                PlatformPaneEvent platformPaneEvent = new PlatformPaneEvent(this);
                onDeleteActionProperty.get().handle(platformPaneEvent);
            }
        });
    }

    public ObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public StringProperty nameProperty() {
        return nameLabel.textProperty();
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public ObjectProperty<EventHandler<PlatformPaneEvent>> onMainActionProperty() {
        return onMainActionProperty;
    }

    public ObjectProperty<EventHandler<PlatformPaneEvent>> onDeleteActionProperty() {
        return onDeleteActionProperty;
    }

}
