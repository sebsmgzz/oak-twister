package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;
import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Accounts.ACCOUNT_PANE)
public class AccountPane extends StackPane implements Initializable {

    private final ViewMediator viewMediator;

    private final SimpleObjectProperty<AccountViewModel> viewModelProperty;
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantsCountProperty;

    @FXML private Button mainButton;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public AccountPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        identifierProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UI
        super.setOnMouseEntered(event -> deleteButton.setVisible(true));
        super.setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);

        // Properties
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

    public void setViewModel(AccountViewModel viewModel) {
        mainButton.setOnAction(this::onMainButtonClick);
        identifierProperty.bind(viewModel.idProperty());
        createdAtProperty.bind(viewModel.createdAtProperty());
        platformNameProperty().bindBidirectional(viewModel.platform().nameProperty());
        imageProperty().bindBidirectional(viewModel.platform().imageProperty());
        grantsCountProperty.bind(viewModel.grants().grantCountProperty());
        deleteButton.setOnAction(this::onDeleteButtonClick);
        viewModelProperty.set(viewModel);
    }

    public AccountViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<AccountViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public StringProperty platformNameProperty() {
        return platformNameLabel.textProperty();
    }

    public ReadOnlyObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public ReadOnlyIntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onDeleteActionProperty() {
        return deleteButton.onActionProperty();
    }

    private void onMainButtonClick(ActionEvent event) {
        // TODO: AccountPane::onMainButtonClick
    }

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

}
