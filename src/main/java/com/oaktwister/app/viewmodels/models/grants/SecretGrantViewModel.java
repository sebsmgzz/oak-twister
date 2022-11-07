package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.SecretGrant;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SecretGrantViewModel extends GrantViewModel<SecretGrant, String> {

    private final SimpleStringProperty hintProperty;

    public SecretGrantViewModel() {
        hintProperty = new SimpleStringProperty();
    }

    @Override
    public SecretGrant getGrant() {
        String name = getName();
        String value = getValue();
        String hint = getHint();
        return new SecretGrant(name, value, hint);
    }

    @Override
    public void setGrant(SecretGrant value) {
        super.setGrant(value);
        setHint(value.getHint());
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
