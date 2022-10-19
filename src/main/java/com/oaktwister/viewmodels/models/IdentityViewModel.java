package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.models.props.Grant;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final Identity identity;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleListProperty<Grant<?>> grantsProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public IdentityViewModel(Identity identity) {
        this.identity = identity;
        idProperty = new SimpleObjectProperty<>(identity.getId());
        grantsProperty = new SimpleListProperty<>();
        createdAtProperty = new SimpleObjectProperty<>(identity.getCreatedAt());
    }

    private void initialize() {
        ObservableList<Grant<?>> grants = FXCollections.observableArrayList();
        for(Grant<?> grant : identity.getGrants()) {
            grants.add(grant);
        }
        grantsProperty.set(grants);
        idProperty.addListener((observable, oldValue, newValue) -> identity.setId(newValue));
        grantsProperty.get().addListener((ListChangeListener<Grant<?>>) c -> {
            // TODO
        });
        createdAtProperty.addListener((observable, oldValue, newValue) ->  identity.setCreatedAt(newValue));
    }

    public SimpleObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public ReadOnlyListProperty<Grant<?>> grantsProperty() {
        return grantsProperty;
    }

    public SimpleObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

}
