package com.oaktwister.views.controls.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.PLATFORMS_PANE)
public class PlatformsPane extends AnchorPane implements Initializable {

    private final SimpleListProperty<PlatformPane> platformPanesProperty;

    private final ListItemAddedListener<PlatformPane> onPlatformPaneAddedListener;
    private final ListItemRemovedListener<PlatformPane> onPlatformPaneRemovedListener;

    @FXML private VBox vbox;
    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public PlatformsPane() {
        super();
        platformPanesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onPlatformPaneAddedListener = new ListItemAddedListener<>(this::onPlatformPaneAdded);
        onPlatformPaneRemovedListener = new ListItemRemovedListener<>(this::onPlatformPaneRemoved);
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefWidth(newValue.doubleValue());
        });
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefHeight(newValue.doubleValue());
        });
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        platformPanesProperty.addListener(onPlatformPaneAddedListener);
        platformPanesProperty.addListener(onPlatformPaneRemovedListener);
    }

    public ListProperty<PlatformPane> platformPanesProperty() {
        return platformPanesProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    private void onPlatformPaneAdded(PlatformPane platformPane) {
        flowPane.getChildren().add(platformPane);
    }

    private void onPlatformPaneRemoved(PlatformPane platformPane) {
        flowPane.getChildren().remove(platformPane);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

/*    private void onAddButtonClick(ActionEvent event) {
        EditPlatformDialog dialog = viewMediator.dialogFactory.getEditPlatformDialog();
        dialog.setOnFinish(platformViewModel -> {
            // TODO: Add platform
            System.out.println("HERE");
            *//*
            viewModelProperty.get().addPlatform(
                    platformViewModel.nameProperty().get(),
                    platformViewModel.imageProperty().get(),
                    platformViewModel.urlProperty().get());
            *//*
        });
        dialog.showAndWait();
    }*/

}
