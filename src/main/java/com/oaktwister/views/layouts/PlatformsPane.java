package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.controls.PlatformPane;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlatformsPane extends VBox implements View {

    private final ViewHandler viewHandler;
    private final PlatformsViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Button addButton;

    private final SimpleListProperty<PlatformPane> platforms;

    @Override
    public String getViewLocation() {
        return Resources.Views.Layouts.PLATFORMS_PANE;
    }

    public PlatformsPane(ViewHandler viewHandler, PlatformsViewModel viewModel) throws IOException {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
        viewHandler.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bindings
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue())));
        addButton.setOnAction(this::onAddButtonClick);
        viewModel.platformsProperty().addListener(this::onPlatformsChange);

        // Load data
        viewModel.loadPlatforms();

    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    private void onAddButtonClick(ActionEvent actionEvent) {
        System.out.println("TODO");
        // TODO: Add PlatformPane to the platforms field
    }

    private void onPlatformsChange(ListChangeListener.Change<? extends PlatformViewModel> change) {
        List<Node> children = flowPane.getChildren();
        while (change.next()) {

            // When a platform is added, simply get the PlatformPane from the viewFactory and
            // add it to the flowPane's children
            if (change.wasAdded()) {
                for (PlatformViewModel pViewModel : change.getAddedSubList()) {
                    try {
                        children.add(viewHandler.getPlatformPane(pViewModel));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            // When a platform is removed, we need to iterate through the flowPane's children to
            // backtrack the Node to the PlatformViewModel been removed
            if (change.wasRemoved()) {
                for (PlatformViewModel pViewModel : change.getAddedSubList()) {
                    for (Node node : children) {
                        PlatformPane platformPane = node instanceof PlatformPane? (PlatformPane) node : null;
                        if(platformPane == null) {
                            throw new RuntimeException(
                                "A PlatformPane::flowPane children was found not to be an instance of PlatformPane. " +
                                "This is not the expected behaviour. Something is critically wrong.");
                        }
                        PlatformViewModel foundViewModel = platformPane.getViewModel();
                        if (pViewModel == foundViewModel) {
                            children.remove(node);
                        }
                    }
                }
            }

        }
    }

}
