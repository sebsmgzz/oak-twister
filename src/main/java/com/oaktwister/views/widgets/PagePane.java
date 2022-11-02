package com.oaktwister.views.widgets;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;

import javafx.beans.property.*;
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

@ViewDescriptor(location = ViewResources.Widgets.PAGE_PANE)
public abstract class PagePane<T extends Node> extends AnchorPane implements Initializable {

    @FXML private VBox vbox;
    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private Button addButton;

    private final SimpleObjectProperty<T> contentProperty;

    public PagePane() {
        contentProperty = new SimpleObjectProperty<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        scrollPane.contentProperty().bind(contentProperty);
    }

    protected ReadOnlyDoubleProperty innerWidthProperty() {
        return scrollPane.widthProperty();
    }

    protected ReadOnlyDoubleProperty innerHeightProperty() {
        return scrollPane.heightProperty();
    }

    protected ObjectProperty<T> contentProperty() {
        return contentProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

}
