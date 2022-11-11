package com.oaktwister.app.views.accounts;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.AccountPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

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

    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;

    public AccountPane() {
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
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
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    public Image getImage() {
        return imageProperty().get();
    }
    public void setImage(Image value) {
        imageProperty().set(value);
    }

    public StringProperty platformNameProperty() {
        return platformNameLabel.textProperty();
    }
    public String getPlatformName() {
        return platformNameProperty().get();
    }
    public void setPlatformName(String value) {
        platformNameProperty().set(value);
    }

    public ObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }
    public UUID getIdentifier() {
        return identifierProperty().get();
    }
    public void setIdentifier(UUID value) {
        identifierProperty().set(value);
    }

    public IntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }
    public Integer getGrantsCount() {
        return grantsCountProperty().get();
    }
    public void setGrantsCount(Integer value) {
        grantsCountProperty().set(value);
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty().get();
    }
    public void setCreatedAt(LocalDateTime value) {
        createdAtProperty().set(value);
    }

}
