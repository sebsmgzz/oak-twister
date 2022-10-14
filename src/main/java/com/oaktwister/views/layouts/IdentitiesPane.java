package com.oaktwister.views.layouts;

import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.controls.IdentityCell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IdentitiesPane extends Pane implements Initializable {

    @FXML private Label titleLabel;
    @FXML private ListView<IdentityViewModel> listView;
    @FXML private Button addButton;

    public IdentitiesPane() throws IOException {
        super();
        URL resourceUrl = IdentitiesPane.class.getResource(Resources.Views.Layouts.IDENTITIES_PANE);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setControllerFactory(aClass -> this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefHeight(newValue.doubleValue())));
        listView.setCellFactory(listView -> {
            try {
                return new IdentityCell();
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
