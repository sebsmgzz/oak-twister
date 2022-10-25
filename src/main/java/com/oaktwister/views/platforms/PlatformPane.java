package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
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

@ViewDescriptor(location = ViewResources.Platforms.PLATFORM_PANE)
public class PlatformPane extends StackPane implements Initializable {

    private final ViewMediator viewMediator;

    private final SimpleObjectProperty<PlatformViewModel> viewModelProperty;
    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public PlatformPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        identifierProperty = new SimpleObjectProperty<>(); // TODO: Initialize empty UUID
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        viewMediator.loadCustomView(this);
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
            createdAtLabel.setText(newValue.toString()); // TODO: Format date
        });

    }

    public void setViewModel(PlatformViewModel viewModel) {
        mainButton.setOnAction(this::onMainButtonClick);
        identifierProperty.bind(viewModel.idProperty());
        imageProperty().bindBidirectional(viewModel.imageProperty());
        nameProperty().bindBidirectional(viewModel.nameProperty());
        createdAtProperty.bind(viewModel.createdAtProperty());
        deleteButton.setOnAction(this::onDeleteButtonClick);
        viewModelProperty.set(viewModel);
    }

    public PlatformViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<PlatformViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

    public ReadOnlyObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public StringProperty nameProperty() {
        return nameLabel.textProperty();
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onDeleteActionProperty() {
        return mainButton.onActionProperty();
    }

    private void onMainButtonClick(ActionEvent event) {
        // TODO: PlatformPane::onMainButtonClick
    }

    private void onDeleteButtonClick(ActionEvent event) {
        // TODO: Move to alert factory? Event system?
        PlatformViewModel viewModel = getViewModel();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete platform");
        alert.setContentText(String.format(
                "Do you really want to delete platform %s?%n" +
                        "This action cannot be undone.",
                viewModel.idProperty().get()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && !result.get().equals(ButtonType.CANCEL)) {
            getViewModel().delete();
        }
    }

}
