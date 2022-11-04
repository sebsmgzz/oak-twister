package com.oaktwister.views.widgets;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
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
public class ImageFrame extends StackPane implements Initializable {

    @FXML private Button mainButton;
    @FXML private Button imageButton;

    public ImageFrame() {
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.setOnMouseEntered(event -> imageButton.setVisible(true));
        super.setOnMouseExited(event -> imageButton.setVisible(false));
        super.hoverProperty().addListener(this::onHover);
        super.setStyle("""
                -fx-background-color: #E8CFB5;
                -fx-background-radius: 10px;
                """);
        mainButton.setStyle("""                
                -fx-cursor: hand;
                -fx-background-color: transparent;
                """);
        imageButton.setStyle("""                
                -fx-background-radius: 5em;
                -fx-background-color: #c12126;
                """);
    }

    private void onHover(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            super.setStyle("""
                -fx-background-color: #E8CFB5;
                -fx-background-radius: 10px;
                """ + """
                -fx-border-color: #c12126;
                -fx-border-width: 3px;
                -fx-border-insets: -3px;
                -fx-border-radius: 10px;
                """);
        } else {
            super.setStyle("""
                -fx-background-color: #E8CFB5;
                -fx-background-radius: 10px;
                """ + """
                -fx-border-width: 0px;
                -fx-border-insets: 0px;
                """);
        }
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onImageActionProperty() {
        return imageButton.onActionProperty();
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
