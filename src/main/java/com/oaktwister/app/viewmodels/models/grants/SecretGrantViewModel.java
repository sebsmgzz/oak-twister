package com.oaktwister.app.viewmodels.models.grants;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SecretGrantViewModel extends GrantViewModel<String> {

    private final SimpleStringProperty hintProperty;

    public SecretGrantViewModel() {
        hintProperty = new SimpleStringProperty();
    }

    public StringProperty hintProperty() {
        return hintProperty;
    }

    public String getHint() {
        return hintProperty().get();
    }

    public void setHint(String value) {
        hintProperty().set(value);
    }

}
