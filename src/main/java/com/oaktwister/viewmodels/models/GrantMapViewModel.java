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
import java.util.Collection;

public class GrantMapViewModel {

    private GrantMap grantMap;

    private final SimpleIntegerProperty grantCountProperty;
    private final SimpleListProperty<Grant<?>> grantsProperty;

    public GrantMapViewModel() {
        grantCountProperty = new SimpleIntegerProperty();
        grantsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void setGrantMap(GrantMap grantMap) {
        if(this.grantMap != null) {
            throw new RuntimeException("GrantMap has already been set");
        }
        this.grantMap = grantMap;

        Collection<Grant<?>> grants = grantMap.grants();
        int grantCount = grants.size();
        grantCountProperty.set(grantCount);

        grantsProperty.setAll(grants);
        grantsProperty.addListener(new ListItemChangeListener<>(grantMap::add, grantMap::remove));
        grantsProperty.addListener((ListChangeListener<Grant<?>>) change -> {
            grantCountProperty.set(grantsProperty.size());
        });
    }

    public ReadOnlyIntegerProperty grantCountProperty() {
        return grantCountProperty;
    }

    public ReadOnlyListProperty<Grant<?>> grantsProperty() {
        return grantsProperty;
    }

}
