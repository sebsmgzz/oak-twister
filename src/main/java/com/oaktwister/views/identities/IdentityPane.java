package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.events.*;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;

import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Identities.PANE)
public class IdentityPane extends StackPane implements Initializable {

    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantsCountProperty;
    private final SimpleObjectProperty<EventHandler<IdentityPaneActionEvent>> onMainActionProperty;
    private final SimpleObjectProperty<EventHandler<IdentityPaneActionEvent>> onDeleteActionProperty;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private Label nameLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public IdentityPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
        onMainActionProperty = new SimpleObjectProperty<>();
        onDeleteActionProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOnMouseEntered(event -> deleteButton.setVisible(true));
        setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(LocalDateTimeUtil.toDefault(newValue));
        });
        grantsCountProperty.addListener((observable, oldValue, newValue) -> {
            grantsCountLabel.setText(newValue.toString());
        });
        deleteButton.setOnAction(actionEvent -> {
            EventHandler<IdentityPaneActionEvent> eventHandler = onDeleteActionProperty.get();
            if(eventHandler != null) {
                IdentityPaneActionEvent event = new IdentityPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
        mainButton.setOnAction(actionEvent -> {
            EventHandler<IdentityPaneActionEvent> eventHandler = onMainActionProperty.get();
            if(eventHandler != null) {
                IdentityPaneActionEvent event = new IdentityPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
    }

    public SimpleObjectProperty<EventHandler<IdentityPaneActionEvent>> onMainActionProperty() {
        return onMainActionProperty;
    }

    public ObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public StringProperty nameProperty() {
        return nameLabel.textProperty();
    }

    public IntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public SimpleObjectProperty<EventHandler<IdentityPaneActionEvent>> onDeleteActionProperty() {
        return onDeleteActionProperty;
    }

/*

    private void onDeleteButtonClick(ActionEvent event) {
        // TODO: Move to alert factory? Event system?
        IdentityViewModel viewModel = getViewModel();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete identity");
        alert.setContentText(String.format(
                "Do you really want to delete identity %s?%n" +
                        "This action cannot be undone.",
                viewModel.idProperty().get()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && !result.get().equals(ButtonType.CANCEL)) {
            getViewModel().delete();
        }
    }
*/

}
