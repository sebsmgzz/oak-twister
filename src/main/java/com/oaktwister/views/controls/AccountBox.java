package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.util.DateTimeStringConverter;
import com.oaktwister.views.util.NumberStringConverter;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class AccountBox extends HBox implements View {

    private final ViewHandler viewHandler;
    private final AccountViewModel viewModel;

    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label accountIdentifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;

    private final ObjectProperty<UUID> accountIdentifier;
    private final SimpleIntegerProperty grantsCount;
    private final ObjectProperty<LocalDateTime> createdAt;

    public AccountBox(ViewHandler viewHandler, AccountViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        accountIdentifier = new SimpleObjectProperty<>(viewModel.getId());
        grantsCount = new SimpleIntegerProperty();
        createdAt = new SimpleObjectProperty<>(viewModel.getCreatedAt());
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.ACCOUNT_CELL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountIdentifierLabel.textProperty().bindBidirectional(accountIdentifier, new UUIDStringConverter());
        grantsCountLabel.textProperty().bindBidirectional(grantsCount, new NumberStringConverter(Integer.class));
        createdAtLabel.textProperty().bindBidirectional(createdAt, new DateTimeStringConverter());
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

    public StringProperty platformNameProperty() {
        return platformNameLabel.textProperty();
    }

    public String getPlatformName() {
        return platformNameProperty().get();
    }

    public void setPlatformName(String platformName) {
        platformNameProperty().set(platformName);
    }

    public ObjectProperty<UUID> accountIdentifierProperty() {
        return accountIdentifier;
    }

    public UUID getAccountIdentifier() {
        return accountIdentifierProperty().get();
    }

    public void setAccountIdentifier(UUID accountIdentifier) {
        accountIdentifierProperty().set(accountIdentifier);
    }

    public IntegerProperty grantsCountProperty() {
        return grantsCount;
    }

    public Integer getGrantsCount() {
        return grantsCountProperty().get();
    }

}
