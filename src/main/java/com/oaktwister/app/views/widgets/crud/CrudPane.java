package com.oaktwister.app.views.widgets.crud;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.CrudPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.PropertyUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.CRUD_PANE)
public class CrudPane<T extends Node> extends AnchorPane implements Initializable {

    public final static String SELECTED_STYLE_CLASS = "selected";

    @FXML private Button button;

    private final SimpleObjectProperty<EventHandler<CrudPaneActionEvent<T>>> onSelectedActionProperty;
    private final SimpleBooleanProperty selectedProperty;
    private final SimpleObjectProperty<T> graphicProperty;

    public CrudPane() {
        onSelectedActionProperty = new SimpleObjectProperty<>();
        selectedProperty = new SimpleBooleanProperty();
        graphicProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnAction(this::onButtonClicked);
        button.graphicProperty().bind(graphicProperty);
        selectedProperty.addListener(this::onSelectedPropertyChanged);
    }

    private void onButtonClicked(ActionEvent actionEvent) {
        selectedProperty.set(true);
        CrudPaneActionEvent<T> event = new CrudPaneActionEvent<T>(this, actionEvent);
        PropertyUtil.tryHandle(onSelectedActionProperty, event);
    }

    private void onSelectedPropertyChanged(ObservableValue<? extends Boolean> observable,
                                           Boolean wasSelected, Boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add(SELECTED_STYLE_CLASS);
        } else {
            button.getStyleClass().remove(SELECTED_STYLE_CLASS);
        }
    }

    public ObjectProperty<EventHandler<CrudPaneActionEvent<T>>> onSelectedActionProperty() {
        return onSelectedActionProperty;
    }
    public EventHandler<CrudPaneActionEvent<T>> getOnSelectedAction() {
        return onSelectedActionProperty().get();
    }
    public void setOnSelectedAction(EventHandler<CrudPaneActionEvent<T>> value) {
        onSelectedActionProperty().set(value);
    }

    public ObjectProperty<T> graphicProperty() {
        return graphicProperty;
    }
    public T getGraphic() {
        return graphicProperty().get();
    }
    public void setGraphic(T value) {
        graphicProperty().set(value);
    }

    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }
    public Boolean getSelected() {
        return selectedProperty().get();
    }
    public void setSelected(Boolean value) {
        selectedProperty().set(value);
    }

}
