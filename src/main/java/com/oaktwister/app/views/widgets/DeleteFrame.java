package com.oaktwister.app.views.widgets;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.IMAGE_FRAME)
public class DeleteFrame extends StackPane implements Initializable {

    @FXML private Button mainButton;
    @FXML private Button deleteButton;

    public DeleteFrame() {
        FXMLUtil.loadControl(DeleteFrame.class, this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setOnMouseEntered(event -> deleteButton.setVisible(true));
        super.setOnMouseExited(event -> deleteButton.setVisible(false));
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnMainAction() {
        return onMainActionProperty().get();
    }

    public void setOnMainAction(EventHandler<ActionEvent> value) {
        onMainActionProperty().set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onDeleteActionProperty() {
        return deleteButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnDeleteAction() {
        return onDeleteActionProperty().get();
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> value) {
        onDeleteActionProperty().set(value);
    }

    public ObjectProperty<Node> graphicProperty() {
        return mainButton.graphicProperty();
    }

    public Node getGraphic() {
        return graphicProperty().get();
    }

    public void setGraphic(Node value) {
        graphicProperty().set(value);
    }

}
