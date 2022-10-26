package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.listeners.*;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.PLATFORMS_PANE)
public class PlatformsPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;

    private final SimpleObjectProperty<PlatformsViewModel> viewModelProperty;
    private final SimpleMapProperty<PlatformViewModel, PlatformPane> platformPanesProperty;

    @FXML private VBox vbox;
    @FXML private Label titleLabel;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Button addButton;

    public PlatformsPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        platformPanesProperty = new SimpleMapProperty<>(FXCollections.observableHashMap());
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UI
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        super.widthProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue()));
        super.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue()));
        addButton.setOnAction(this::onAddButtonClick);

        // Properties
        platformPanesProperty.addListener(new MapItemAddedListener<>((platformViewModel, platformPane) -> {
            flowPane.getChildren().add(platformPane);
        }));
        platformPanesProperty.addListener(new MapItemRemovedListener<>((platformViewModel, platformPane) -> {
            flowPane.getChildren().remove(platformPane);
        }));

    }

    public void setViewModel(PlatformsViewModel viewModel) {
        viewModel.platformsProperty().addListener(new ListItemAddedListener<>(platformViewModel -> {
            PlatformPane platformPane = viewMediator.controlFactory.getPlatformPane(platformViewModel);
            platformPanesProperty.get().put(platformViewModel, platformPane);
        }));
        viewModel.platformsProperty().addListener(new ListItemRemovedListener<>(platformViewModel -> {
            platformPanesProperty.get().remove(platformViewModel);
        }));
        viewModelProperty.set(viewModel);
        viewModel.loadPlatforms();
    }

    public PlatformsViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<PlatformsViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    private void onAddButtonClick(ActionEvent event) {
        EditPlatformDialogPane dialogPane = viewMediator.dialogFactory.getEditPlatformDialogPane();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        Optional<ButtonType> dialogResult = dialog.showAndWait();
        if(dialogResult.isPresent() && dialogResult.get().equals(ButtonType.FINISH)) {
            // TODO: Add dialogPane::viewModel to PlatformsPane::platformPanes
            PlatformViewModel viewModel = dialogPane.getViewModel();
            String name = viewModel.nameProperty().get();
            System.out.println(name);
        }
    }

}
