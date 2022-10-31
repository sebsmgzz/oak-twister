package com.oaktwister.views.controls;

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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Controls.PAGE_PANE)
public class PagePane<T extends Node> extends AnchorPane implements Initializable {

    private final SimpleListProperty<T> panesProperty;

    private final ListItemAddedListener<T> onPaneAddedListener;
    private final ListItemRemovedListener<T> onPaneRemovedListener;

    @FXML private VBox vbox;
    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public PagePane() {
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
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefHeight(newValue.doubleValue());
        });
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        panesProperty.addListener(onPaneAddedListener);
        panesProperty.addListener(onPaneRemovedListener);
    }

    public ListProperty<T> panesProperty() {
        return panesProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    private void onPaneAdded(T pane) {
        flowPane.getChildren().add(pane);
    }

    private void onPaneRemoved(T pane) {
        flowPane.getChildren().remove(pane);
    }

}
