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

    private final ObjectProperty<UUID> idProperty;
    private final SimpleIntegerProperty grantsCountProperty;
    private final ObjectProperty<LocalDateTime> createdAt;

    public AccountBox(ViewHandler viewHandler, AccountViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        idProperty = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        grantsCountProperty = new SimpleIntegerProperty(-1);
        createdAt = new SimpleObjectProperty<>(LocalDateTime.MIN);
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.ACCOUNT_CELL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                accountIdentifierLabel.setText(newValue.toString()));
        viewModel.grants().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsCountLabel.setText(String.valueOf(newValue.intValue())));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.textProperty().set(newValue.toString())); // TODO: Use date time formatter
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

    public ObjectProperty<UUID> idPropertyProperty() {
        return idProperty;
    }

    public UUID getIdProperty() {
        return idPropertyProperty().get();
    }

    public void setIdProperty(UUID idProperty) {
        idPropertyProperty().set(idProperty);
    }

    public IntegerProperty grantsCountPropertyProperty() {
        return grantsCountProperty;
    }

    public Integer getGrantsCountProperty() {
        return grantsCountPropertyProperty().get();
    }

}
