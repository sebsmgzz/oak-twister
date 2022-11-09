package com.oaktwister.app.views.accounts;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.AccountPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import com.oaktwister.app.views.widgets.DeleteFrame;
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

    @FXML private DeleteFrame deleteFrame;
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
        FXMLUtil.loadControl(this);
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
        deleteFrame.onDeleteActionProperty().set(actionEvent -> {
            EventHandler<AccountPaneActionEvent> eventHandler = onDeleteActionProperty.get();
            if(eventHandler != null) {
                AccountPaneActionEvent event = new AccountPaneActionEvent(this, actionEvent);
                eventHandler.handle(event);
            }
        });
        deleteFrame.onMainActionProperty().set(actionEvent -> {
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

}
