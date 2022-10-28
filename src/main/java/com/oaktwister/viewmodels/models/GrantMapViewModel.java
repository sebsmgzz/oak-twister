package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.grants.Grant;
import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.utils.listeners.ListItemChangeListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
