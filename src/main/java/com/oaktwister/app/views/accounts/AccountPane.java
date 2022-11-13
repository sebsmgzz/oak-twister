package com.oaktwister.app.views.accounts;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import com.oaktwister.app.viewmodels.models.AccountViewModel;
import javafx.beans.property.*;
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
    private final SimpleObjectProperty<AccountViewModel> accountProperty;

    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;

    public AccountPane() {
        accountProperty = new SimpleObjectProperty<>();
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
        accountProperty.addListener((observable, oldValue, newValue) -> {
            identifierProperty.bind(newValue.idProperty());
            createdAtProperty.bind(newValue.createdAtProperty());
            imageView.imageProperty().bind(newValue.platform().imageProperty());
            platformNameLabel.textProperty().bind(newValue.platform().nameProperty());
            grantsCountProperty.bind(newValue.grantMap().grantCountProperty());
        });
    }

    public ObjectProperty<AccountViewModel> accountProperty() {
        return accountProperty;
    }
    public AccountViewModel getAccount() {
        return accountProperty().get();
    }
    public void setAccount(AccountViewModel value) {
        accountProperty().set(value);
    }

    public ReadOnlyObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    public Image getImage() {
        return imageProperty().get();
    }

    public ReadOnlyStringProperty platformNameProperty() {
        return platformNameLabel.textProperty();
    }
    public String getPlatformName() {
        return platformNameProperty().get();
    }

    public ReadOnlyObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }
    public UUID getIdentifier() {
        return identifierProperty().get();
    }

    public ReadOnlyIntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }
    public Integer getGrantsCount() {
        return grantsCountProperty().get();
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty().get();
    }

}
