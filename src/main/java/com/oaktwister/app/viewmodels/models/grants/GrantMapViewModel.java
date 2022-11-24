package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.GrantMap;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.Collection;

public class GrantMapViewModel {

    private final ViewModelFactory viewModelFactory;

    private final SimpleListProperty<?> grantsProperty;

    public GrantMapViewModel(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        grantsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void setGrantMap(GrantMap grantMap) {
        Collection<Grant<?>> grants = grantMap.grants();
        for(Grant<?> grant : grants) {
        }
    }

    public GrantMap getGrantMap() {
        return null;
    }

    public ReadOnlyIntegerProperty grantCountProperty() {
        return grantsProperty.sizeProperty();
    }

}
