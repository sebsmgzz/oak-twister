package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IdentitiesPane extends Pane implements View {

    private final ViewHandler viewHandler;

    @FXML private Label titleLabel;
    @FXML private ListView<IdentityViewModel> listView;
    @FXML private Button addButton;

    @Override
    public String getViewLocation() {
        return Resources.Views.Layouts.IDENTITIES_PANE;
    }

    public IdentitiesPane(ViewHandler viewHandler) throws IOException {
        super();
        this.viewHandler = viewHandler;
        viewHandler.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefHeight(newValue.doubleValue())));
        listView.setCellFactory(listView -> {
            try {
                return viewHandler.getIdentityCell();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // TODO: How is the IdentityCell reading the IdentityViewModel?
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
