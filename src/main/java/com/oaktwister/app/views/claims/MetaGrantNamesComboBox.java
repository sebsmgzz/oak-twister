package com.oaktwister.app.views.claims;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.domain.models.claims.MetaGrant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Claims.NAMES_COMBO_BOX)
public class MetaGrantNamesComboBox extends AnchorPane implements Initializable {

    // UI
    @FXML private ComboBox<String> comboBox;

    public MetaGrantNamesComboBox() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setButtonCell(new MetaGrantNamesComboBoxCell());
        comboBox.setCellFactory(listView -> new MetaGrantNamesComboBoxCell());
        addMetaGrantNames(MetaGrant.getAll().stream().map(MetaGrant::getName).toList().toArray(new String[0]));
    }

    public ObjectProperty<String> selectedMetaGrantNameProperty() {
        return comboBox.valueProperty();
    }
    public String getSelectedMetaGrantName() {
        return selectedMetaGrantNameProperty().get();
    }
    public void setSelectedMetaGrantName(String value) {
        selectedMetaGrantNameProperty().set(value);
    }

    public ReadOnlyObjectProperty<ObservableList<String>> metaGrantNamesProperty() {
        return comboBox.itemsProperty();
    }
    public ObservableList<String> getMetaGrantNames() {
        return metaGrantNamesProperty().get();
    }
    public boolean addMetaGrantName(String metaGrantName) {
        return getMetaGrantNames().add(metaGrantName);
    }
    public boolean addMetaGrantNames(String... metaGrantNames) {
        return getMetaGrantNames().addAll(metaGrantNames);
    }
    public boolean removeMetaGrantName(String metaGrantName) {
        return getMetaGrantNames().remove(metaGrantName);
    }
    public boolean removeMetaGrantNames(String... metaGrantNames) {
        return getMetaGrantNames().remove(metaGrantNames);
    }
    public void clearMetaGrantNames() {
        getMetaGrantNames().clear();
    }

}
