package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.Grant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class GrantViewModel<T> {

    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<T> valueProperty;

    public GrantViewModel() {
        nameProperty = new SimpleStringProperty();
        valueProperty = new SimpleObjectProperty<>();
    }

    public abstract Grant<T> getGrant();

    public abstract void setGrant(Grant<T> value);

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String value) {
        nameProperty().set(value);
    }

    public ObjectProperty<T> valueProperty() {
        return valueProperty;
    }

    public T getValue() {
        return valueProperty().get();
    }

    public void setValue(T value) {
        valueProperty().set(value);
    }

}
