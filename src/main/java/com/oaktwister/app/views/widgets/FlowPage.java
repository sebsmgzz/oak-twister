package com.oaktwister.app.views.widgets;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.FLOW_PANE)
public class FlowPage<T extends Node> extends AnchorPane implements Initializable {

    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;

    private final SimpleListProperty<T> panesProperty;

    private final ListItemAddedListener<T> onPaneAddedListener;
    private final ListItemRemovedListener<T> onPaneRemovedListener;

    public FlowPage() {
        panesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onPaneAddedListener = new ListItemAddedListener<>(this::onPaneAdded);
        onPaneRemovedListener = new ListItemRemovedListener<>(this::onPaneRemoved);
        FXMLUtil.loadControl(FlowPage.class, this);
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

    public ListProperty<T> panesProperty() {
        return panesProperty;
    }

    public ObservableList<T> getPanes() {
        return panesProperty().get();
    }

    public void setPanes(ObservableList<T> value) {
        panesProperty().set(value);
    }

    private void onPaneAdded(T pane) {
        flowPane.getChildren().add(pane);
    }

    private void onPaneRemoved(T pane) {
        flowPane.getChildren().remove(pane);
    }

}
