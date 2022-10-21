package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewHandler;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.PLATFORMS_PANE)
public class PlatformsPane extends AnchorPane implements Initializable {

    private final ViewHandler viewHandler;
    private final PlatformsViewModel viewModel;

    @FXML private VBox vbox;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;

    private final SimpleListProperty<PlatformPane> platforms;

    public PlatformsPane(ViewHandler viewHandler, PlatformsViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
        viewHandler.loadCustomView(this);
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
        PlatformPane platformPane = viewHandler.getPlatformPane(platformViewModel);
        flowPane.getChildren().add(platformPane);
    }

    // When a platform is removed, we need to iterate through the flowPane's children to
    // backtrack the Node to the PlatformViewModel been removed
    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        List<Node> children = flowPane.getChildren();
        for (Node node : children) {
            PlatformPane platformPane = node instanceof PlatformPane? (PlatformPane) node : null;
            if(platformPane == null) {
                throw new RuntimeException(
                    "A PlatformPane::flowPane children was found not to be an instance of PlatformPane. " +
                            "This is not the expected behaviour. Something is critically wrong.");
            }
            PlatformViewModel foundViewModel = platformPane.getViewModel();
            if (platformViewModel == foundViewModel) {
                children.remove(node);
            }
        }
    }

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) {
        // TODO: Add PlatformPane to the platforms field
    }

}
