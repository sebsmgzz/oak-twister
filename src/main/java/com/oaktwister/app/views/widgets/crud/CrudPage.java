package com.oaktwister.app.views.widgets.crud;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.CrudPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.CRUD_PAGE)
public class CrudPage<T extends Node> extends AnchorPane implements Initializable {

    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;

    private final HashMap<T, CrudPane<T>> paneMapping;
    private final SimpleObjectProperty<CrudPane<T>> selectedPaneProperty;

    public CrudPage() {
        paneMapping = new HashMap<>();
        selectedPaneProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefWidth(newValue.doubleValue());
        });
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            flowPane.setPrefHeight(newValue.doubleValue());
        });
    }

    private void onPaneSelected(CrudPaneActionEvent<T> actionEvent) {
        CrudPane<T> selectedPane = actionEvent.getSender();
        selectedPaneProperty.set(selectedPane);
        for (CrudPane<T> pane : paneMapping.values()) {
            if(pane != selectedPane) {
                pane.selectedProperty().set(false);
            }
        }
    }

    public boolean add(T pane) {
        CrudPane<T> crudPane = new CrudPane<>();
        crudPane.setGraphic(pane);
        crudPane.setOnSelectedAction(this::onPaneSelected);
        paneMapping.put(pane, crudPane);
        return flowPane.getChildren().add(crudPane);
    }

    public boolean remove(T pane) {
        CrudPane<T> crudPane = paneMapping.get(pane);
        return flowPane.getChildren().remove(crudPane);
    }

    public ReadOnlyObjectProperty<CrudPane<T>> selectedPaneProperty() {
        return selectedPaneProperty;
    }
    public CrudPane<T> getSelectedPaneProperty() {
        return selectedPaneProperty().get();
    }

}
