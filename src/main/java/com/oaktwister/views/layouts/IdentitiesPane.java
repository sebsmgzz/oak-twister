package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IdentitiesPane extends VBox implements View {

    private final ViewHandler viewHandler;
    private final IdentitiesViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public IdentitiesPane(ViewHandler viewHandler, IdentitiesViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Layouts.IDENTITIES_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bindings
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue())));

        // Data loaders
        viewModel.loadIdentities();

    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAddAction() {
        return onAddActionProperty().get();
    }

    public void setOnAddAction(EventHandler<ActionEvent> onAddAction) {
        onAddActionProperty().set(onAddAction);
    }

}
