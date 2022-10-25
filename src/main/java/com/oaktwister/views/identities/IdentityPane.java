package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Identities.IDENTITY_PANE)
public class IdentityPane extends StackPane implements Initializable {

    private final ViewMediator viewMediator;

    private final SimpleObjectProperty<IdentityViewModel> viewModelProperty;
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantsCountProperty;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public IdentityPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        identifierProperty = new SimpleObjectProperty<>(); // TODO: Initialize empty UUID
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantsCountProperty = new SimpleIntegerProperty();
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UI
        setOnMouseEntered(event -> deleteButton.setVisible(true));
        setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);

        // Properties
        identifierProperty.addListener((observer, oldValue, newValue) -> {
            identifierLabel.setText(newValue.toString());
        });
        createdAtProperty.addListener((observer, oldValue, newValue) -> {
            createdAtLabel.setText(newValue.toString()); // TODO: Use datetime formatter
        });
        grantsCountProperty.addListener((observable, oldValue, newValue) -> {
            grantsCountLabel.setText(newValue.toString());
        });

    }

    public void setViewModel(IdentityViewModel viewModel) {
        mainButton.setOnAction(this::onMainButtonClick);
        identifierProperty.bind(viewModel.idProperty());
        grantsCountProperty.bind(viewModel.grants().grantCountProperty());
        createdAtProperty.bind(viewModel.createdAtProperty());
        deleteButton.setOnAction(this::onDeleteButtonClick);
    }

    public IdentityViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<IdentityViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
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

    private void onMainButtonClick(ActionEvent event) {
        // TODO: IdentityPane::onMainButtonClick
    }

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

}
