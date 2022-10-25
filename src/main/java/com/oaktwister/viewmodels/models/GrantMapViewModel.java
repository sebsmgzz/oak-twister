package com.oaktwister.viewmodels.models;

import com.oaktwister.models.grants.Grant;
import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.utils.listeners.ListItemChangeListener;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class GrantMapViewModel {

    private GrantMap grantMap;
    private final SimpleIntegerProperty grantCountProperty;
    private final SimpleListProperty<Grant<?>> grantsProperty;

    public GrantMapViewModel() {
        this.grantCountProperty = new SimpleIntegerProperty(-1);
        this.grantsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void bind(GrantMap grantMap) {
        this.grantMap = grantMap;

        grantCountProperty.set(grantMap.grants().size());

        grantsProperty.set(FXCollections.observableArrayList(grantMap.grants()));
        grantsProperty.addListener((ListChangeListener<Grant<?>>) change ->
                grantCountProperty.set(grantsProperty.size()));
        grantsProperty.addListener(new ListItemChangeListener<>(grantMap::add, grantMap::remove));
    }

    public ReadOnlyIntegerProperty grantCountProperty() {
        return grantCountProperty;
    }

    public ReadOnlyListProperty<Grant<?>> grantsProperty() {
        return grantsProperty;
    }

}
