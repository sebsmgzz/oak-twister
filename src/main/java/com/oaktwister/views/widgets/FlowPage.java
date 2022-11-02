package com.oaktwister.views.widgets;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.PAGE_PANE)
public class FlowPage<T extends Node> extends PagePane<FlowPane> implements Initializable {

    private FlowPane flowPane;

    private final SimpleListProperty<T> panesProperty;

    private final ListItemAddedListener<T> onPaneAddedListener;
    private final ListItemRemovedListener<T> onPaneRemovedListener;

    public FlowPage() {
        flowPane = new FlowPane();
        panesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onPaneAddedListener = new ListItemAddedListener<>(this::onPaneAdded);
        onPaneRemovedListener = new ListItemRemovedListener<>(this::onPaneRemoved);
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        innerWidthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefWidth(newValue.doubleValue());
        });
        innerHeightProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefHeight(newValue.doubleValue());
        });
        contentProperty().set(flowPane);
        panesProperty.addListener(onPaneAddedListener);
        panesProperty.addListener(onPaneRemovedListener);
        flowPane.getStyleClass().add("flow-pane");
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
    }

    public ListProperty<T> panesProperty() {
        return panesProperty;
    }

    private void onPaneAdded(T pane) {
        flowPane.getChildren().add(pane);
    }

    private void onPaneRemoved(T pane) {
        flowPane.getChildren().remove(pane);
    }

}
