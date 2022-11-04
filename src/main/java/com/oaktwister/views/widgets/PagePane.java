package com.oaktwister.views.widgets;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.PAGE_PANE)
public class PagePane extends VBox implements Initializable {

    @FXML private AnchorPane anchorPane;
    @FXML private Button addButton;

    private final SimpleObjectProperty<Node> contentProperty;

    public PagePane() {
        contentProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contentProperty.addListener((observable, oldValue, newValue) -> {
            anchorPane.getChildren().remove(oldValue);
            anchorPane.getChildren().add(newValue);
            AnchorPane.setTopAnchor(newValue,0.0 );
            AnchorPane.setRightAnchor(newValue,0.0 );
            AnchorPane.setBottomAnchor(newValue,0.0 );
            AnchorPane.setLeftAnchor(newValue,0.0 );
        });
    }

    public ObjectProperty<Node> contentProperty() {
        return contentProperty;
    }

    public Node getContent() {
        return contentProperty().get();
    }

    public void setContent(Node value) {
        contentProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAddAction() {
        return onAddActionProperty().get();
    }

    public void setOnAddAction(EventHandler<ActionEvent> value) {
        onAddActionProperty().set(value);
    }

}
