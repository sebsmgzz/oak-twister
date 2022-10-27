package com.oaktwister.views.controls.identities;

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

@ViewDescriptor(location = ViewResources.Identities.IDENTITIES_PANE)
public class IdentitiesPane extends AnchorPane implements Initializable {

    private final SimpleListProperty<IdentityPane> identityPanesProperty;

    private final ListItemAddedListener<IdentityPane> onIdentityPaneAddedListener;
    private final ListItemRemovedListener<IdentityPane> onIdentityPaneRemovedListener;

    @FXML private VBox vbox;
    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public IdentitiesPane() {
        super();
        identityPanesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onIdentityPaneAddedListener = new ListItemAddedListener<>(this::onIdentityPaneAdded);
        onIdentityPaneRemovedListener = new ListItemRemovedListener<>(this::onIdentityPaneRemoved);
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
        identityPanesProperty.addListener(onIdentityPaneAddedListener);
        identityPanesProperty.addListener(onIdentityPaneRemovedListener);
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public ListProperty<IdentityPane> identityPanesProperty() {
        return identityPanesProperty;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    private void onIdentityPaneAdded(IdentityPane identityPane) {
        flowPane.getChildren().add(identityPane);
    }

    private void onIdentityPaneRemoved(IdentityPane identityPane) {
        flowPane.getChildren().remove(identityPane);
    }

}
