package com.oaktwister.app.views.widgets.crud;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.CRUD_FRAME)
public class CrudFrame extends VBox implements Initializable {

    // UI
    @FXML private AnchorPane anchorPane;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    // Properties
    private final SimpleObjectProperty<Node> contentProperty;

    public CrudFrame() {
        contentProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contentProperty.addListener((observable, oldValue, newValue) -> {
            ObservableList<Node> children = anchorPane.getChildren();
            children.remove(oldValue);
            children.add(newValue);
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

    public ObjectProperty<EventHandler<ActionEvent>> onEditActionProperty() {
        return editButton.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnEditAction() {
        return onEditActionProperty().get();
    }
    public void setOnEditAction(EventHandler<ActionEvent> value) {
        onEditActionProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onRemoveActionProperty() {
        return removeButton.onActionProperty();
    }
    public EventHandler<ActionEvent> getOnRemoveAction() {
        return onAddActionProperty().get();
    }
    public void setOnRemoveAction(EventHandler<ActionEvent> value) {
        onAddActionProperty().set(value);
    }

}
