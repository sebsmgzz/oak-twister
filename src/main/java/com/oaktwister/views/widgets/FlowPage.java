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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.FLOW_PANE)
public class FlowPage<T extends Node> extends AnchorPane implements Initializable {

    @FXML private PagePane pagePane;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;

    private final SimpleListProperty<T> panesProperty;

    private final ListItemAddedListener<T> onPaneAddedListener;
    private final ListItemRemovedListener<T> onPaneRemovedListener;

    public FlowPage() {
        panesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onPaneAddedListener = new ListItemAddedListener<>(this::onPaneAdded);
        onPaneRemovedListener = new ListItemRemovedListener<>(this::onPaneRemoved);
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefWidth(newValue.doubleValue());
        });
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefHeight(newValue.doubleValue());
        });
        panesProperty.addListener(onPaneAddedListener);
        panesProperty.addListener(onPaneRemovedListener);
    }

    public PagePane page() {
        return pagePane;
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
