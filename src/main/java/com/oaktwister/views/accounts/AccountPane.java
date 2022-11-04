package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.events.AccountPaneActionEvent;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;

import com.oaktwister.views.widgets.ImageFrame;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

@ViewDescriptor(location = ViewResources.Accounts.PANE)
public class AccountPane extends AnchorPane implements Initializable {

    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantsCountProperty;
    private final SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onDeleteActionProperty;
    private final SimpleObjectProperty<EventHandler<AccountPaneActionEvent>> onMainActionProperty;

    @FXML private ImageFrame imageFrame;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;

    public AccountPane() {
        super();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
        onDeleteActionProperty = new SimpleObjectProperty<>();
        onMainActionProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(LocalDateTimeUtil.toDefault(newValue));
        });
        grantsCountProperty.addListener((observable, oldValue, newValue) -> {
            grantsCountLabel.setText(newValue.toString());
        });
        imageFrame.onImageActionProperty().set(actionEvent -> {
            EventHandler<AccountPaneActionEvent> eventHandler = onDeleteActionProperty.get();
            if(eventHandler != null) {
                AccountPaneActionEvent event = new AccountPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
        imageFrame.onMainActionProperty().set(actionEvent -> {
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
