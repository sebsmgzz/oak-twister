package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.models.props.Grant;
import com.oaktwister.models.props.GrantMap;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final Identity identity;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<GrantMap> grantsProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public IdentityViewModel(Identity identity) {
        this.identity = identity;
        idProperty = new SimpleObjectProperty<>(identity.getId());
        grantsProperty = new SimpleObjectProperty<GrantMap>(identity.getGrants());
        createdAtProperty = new SimpleObjectProperty<>(identity.getCreatedAt());
    }

    private void initialize() {
        idProperty.addListener((observable, oldValue, newValue) -> identity.setId(newValue));
        grantsProperty.addListener((observable, oldValue, newValue) -> identity.setGrants(newValue));
        createdAtProperty.addListener((observable, oldValue, newValue) ->  identity.setCreatedAt(newValue));
    }

    public SimpleObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public SimpleObjectProperty<GrantMap> grantsProperty() {
        return grantsProperty;
    }

    public SimpleObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

}
