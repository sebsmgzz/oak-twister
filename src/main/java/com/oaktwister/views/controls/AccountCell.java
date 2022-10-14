package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.util.NumberStringConverter;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AccountCell extends ListCell<AccountViewModel> implements View {

    private final ViewHandler viewHandler;

    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label accountIdentifierLabel;
    @FXML private Label claimsCountLabel;

    private final ObjectProperty<UUID> accountIdentifier;
    private final SimpleIntegerProperty claimsCount;

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.ACCOUNT_CELL;
    }

    public AccountCell(ViewHandler viewHandler) throws IOException {
        super();
        this.viewHandler = viewHandler;
        accountIdentifier = new SimpleObjectProperty<>();
        claimsCount = new SimpleIntegerProperty();
        viewHandler.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountIdentifierLabel.textProperty().bindBidirectional(accountIdentifier, new UUIDStringConverter());
        claimsCountLabel.textProperty().bindBidirectional(claimsCount, new NumberStringConverter(Integer.class));
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

    public IntegerProperty claimsCountProperty() {
        return claimsCount;
    }

    public Integer getClaimsCount() {
        return claimsCountProperty().get();
    }

    public void setClaimsCount(Integer claimsCount) {
        claimsCountProperty().set(claimsCount);
    }

}
