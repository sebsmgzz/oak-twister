package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.GRANT_TYPES_COMBO_BOX)
public class GrantTypesComboBox extends AnchorPane implements Initializable {

    @FXML private ComboBox<String> comboBox;

    public GrantTypesComboBox() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setButtonCell(new GrantTypesComboBoxCell());
        comboBox.setCellFactory(listView -> new GrantTypesComboBoxCell());
    }

    public ObjectProperty<EventHandler<Event>> onShowingProperty() {
        return comboBox.onShowingProperty();
    }
    public EventHandler<Event> getOnShowing() {
        return onShowingProperty().get();
    }
    public void setOnShowing(EventHandler<Event> value) {
        onShowingProperty().set(value);
    }

    public ObjectProperty<String> selectedGrantTypeProperty() {
        return comboBox.valueProperty();
    }
    public String getSelectedGrantType() {
        return selectedGrantTypeProperty().get();
    }
    public void setSelectedGrantType(String value) {
        selectedGrantTypeProperty().set(value);
    }

    public ReadOnlyObjectProperty<ObservableList<String>> grantTypesProperty() {
        return comboBox.itemsProperty();
    }
    public ObservableList<String> getGrantTypes() {
        return grantTypesProperty().get();
    }
    public boolean addGrantType(String drive) {
        return getGrantTypes().add(drive);
    }
    public boolean addGrantTypes(String... drives) {
        return getGrantTypes().addAll(drives);
    }
    public boolean removeGrantType(String drive) {
        return getGrantTypes().remove(drive);
    }
    public boolean removeGrantTypes(String... drives) {
        return getGrantTypes().remove(drives);
    }
    public void clearGrantTypes() {
        getGrantTypes().clear();
    }

}
