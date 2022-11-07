package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.Grant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class GrantViewModel<G extends Grant<V>, V> {

    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<V> valueProperty;

    public GrantViewModel() {
        nameProperty = new SimpleStringProperty();
        valueProperty = new SimpleObjectProperty<>();
    }

    public abstract G getGrant();

    public void setGrant(G grant) {
        setName(grant.getName());
        setValue(grant.getValue());
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String value) {
        nameProperty().set(value);
    }

    public ObjectProperty<V> valueProperty() {
        return valueProperty;
    }

    public V getValue() {
        return valueProperty().get();
    }

    public void setValue(V value) {
        valueProperty().set(value);
    }

}
