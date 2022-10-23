package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.pages.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.util.listeners.DualChangeListener;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.PLATFORMS_PANE)
public class PlatformsPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;
    private final PlatformsViewModel viewModel;

    @FXML private VBox vbox;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;

    private final SimpleListProperty<PlatformPane> platforms;

    public PlatformsPane(ViewMediator viewMediator, PlatformsViewModel viewModel) {
        super();
        this.viewMediator = viewMediator;
        this.viewModel = viewModel;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
        viewMediator.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bindings
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        this.widthProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue()));
        this.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue()));

        viewModel.platformsProperty().addListener(new DualChangeListener<>(
                this::onPlatformViewModelAdded, this::onPlatformViewModelRemoved));

        // Load data
        viewModel.loadPlatforms();

    }

    // When a platform is added, simply get the PlatformPane from the viewFactory and
    // add it to the flowPane's children
    private void onPlatformViewModelAdded(PlatformViewModel platformViewModel) {

        // Bindings
        platformViewModel.onDeleteAccountProperty().set(event -> {
            PlatformViewModel viewModel = event.getPlatformViewModel();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete platform");
            alert.setContentText(String.format(
                    "Do you really want to delete platform %s?%n" +
                            "This action cannot be undone.",
                    viewModel.idProperty().get()));
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isEmpty() || result.get().equals(ButtonType.CANCEL)) {
                event.cancel();
            }
            this.viewModel.platformsProperty().remove(viewModel);
        });

        PlatformPane platformPane = viewMediator.controls().getPlatformPane(platformViewModel);
        flowPane.getChildren().add(platformPane);
    }

    // When a platform is removed, we need to iterate through the flowPane's children to
    // backtrack the Node to the PlatformViewModel been removed
    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        Iterator<Node> iterator = flowPane.getChildren().iterator();
        while(iterator.hasNext()) {
            Node node = iterator.next();
            PlatformPane platformPane = node instanceof PlatformPane? (PlatformPane) node : null;
            if(platformPane == null) {
                throw new RuntimeException(
                        "A PlatformPane::flowPane children was found not to be an instance of PlatformPane. " +
                                "This is not the expected behaviour. Something is critically wrong.");
            }
            PlatformViewModel foundViewModel = platformPane.getViewModel();
            if (platformViewModel == foundViewModel) {
                iterator.remove();
            }
        }
    }

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) {
        // TODO: Add PlatformPane to the platforms field
    }

}
