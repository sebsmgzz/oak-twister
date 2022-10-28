package com.oaktwister.views.controls.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Controls.PLATFORM_PANE)
public class PlatformPane extends StackPane implements Initializable {

    private final SimpleObjectProperty<EventHandler<PlatformPaneEvent>> onMainActionProperty;
    private final SimpleObjectProperty<EventHandler<PlatformPaneEvent>> onDeleteActionProperty;
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public PlatformPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onMainActionProperty = new SimpleObjectProperty<>();
        onDeleteActionProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setOnMouseEntered(event -> deleteButton.setVisible(true));
        super.setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(LocalDateTimeUtil.toDefault(newValue));
        });
        mainButton.setOnAction(this::onMainButtonClick);
        deleteButton.setOnAction(this::onDeleteButtonClick);
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

    private void onMainButtonClick(ActionEvent actionEvent) {
        if (onMainActionProperty.isNotNull().get()) {
            PlatformPaneEvent platformPaneEvent = new PlatformPaneEvent(this);
            onMainActionProperty.get().handle(platformPaneEvent);
        }
    }

    private void onDeleteButtonClick(ActionEvent actionEvent) {
        if (onDeleteActionProperty.isNotNull().get()) {
            PlatformPaneEvent platformPaneEvent = new PlatformPaneEvent(this);
            onDeleteActionProperty.get().handle(platformPaneEvent);
        }
    }

}
