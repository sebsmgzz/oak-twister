package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.events.AccountPaneActionEvent;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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

@ViewDescriptor(location = ViewResources.Accounts.PANE)
public class AccountPane extends StackPane implements Initializable {

    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantsCountProperty;
    private final SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onDeleteActionProperty;
    private final SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onMainActionProperty;

    @FXML private Button mainButton;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public AccountPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
        onDeleteActionProperty = new SimpleObjectProperty<>();
        onMainActionProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    private void onHover(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            super.setStyle("""
                -fx-background-color: #E8CFB5; 
                -fx-background-radius: 10px;
                
                -fx-border-color: #c12126;
                -fx-border-width: 3px;
                -fx-border-insets: -3px;
                -fx-border-radius: 10px;
                """);
        } else {
            super.setStyle("""
                -fx-background-color: #E8CFB5; 
                -fx-background-radius: 10px;
                
                -fx-border-width: 0px;
                -fx-border-insets: 0px;
                """);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setOnMouseEntered(event -> deleteButton.setVisible(true));
        super.setOnMouseExited(event -> deleteButton.setVisible(false));
        super.hoverProperty().addListener(this::onHover);
        super.setStyle("""
                -fx-background-color: #E8CFB5;
                -fx-background-radius: 10px;
                """);
        mainButton.setStyle("""                
                -fx-cursor: hand;
                -fx-background-color: transparent;
                """);
        deleteButton.setStyle("""                
                -fx-background-radius: 5em;
                -fx-background-color: #c12126;
                """);
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
            EventHandler<AccountPaneActionEvent> eventHandler = onDeleteActionProperty.get();
            if(eventHandler != null) {
                AccountPaneActionEvent event = new AccountPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
        mainButton.setOnAction(actionEvent -> {
            EventHandler<AccountPaneActionEvent> eventHandler = onMainActionProperty.get();
            if(eventHandler != null) {
                AccountPaneActionEvent event = new AccountPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
    }

    public SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onMainActionProperty() {
        return onMainActionProperty;
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public StringProperty platformNameProperty() {
        return platformNameLabel.textProperty();
    }

    public ObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public IntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onDeleteActionProperty() {
        return onDeleteActionProperty;
    }

    /*
    private void onDeleteButtonClick(ActionEvent event) {
        // TODO: Move to alert factory? Event system?
        AccountViewModel accountViewModel = getViewModel();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete account");
        alert.setContentText(String.format(
                "Do you really want to delete account %s? %nThis action cannot be undone.",
                accountViewModel.idProperty().get()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && !ButtonType.CANCEL.equals(result.get())) {
            getViewModel().delete();
        }
    }
    */

}