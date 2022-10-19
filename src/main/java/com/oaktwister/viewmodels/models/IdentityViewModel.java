package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.models.props.Grant;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;

    private Identity identity;
    private GrantMapViewModel grantMap;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleIntegerProperty grantCountProperty;
    private final SimpleListProperty<Grant<?>> grantsProperty;

    public IdentityViewModel(IdentitiesRepo identitiesRepo) {
        this.identitiesRepo = identitiesRepo;
        grantMap = new GrantMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        grantCountProperty = new SimpleIntegerProperty(-1);
        grantsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void bind(Identity identity) {
        this.identity = identity;

        idProperty.set(identity.getId());
        idProperty.addListener((observable, oldValue, newValue) -> identity.setId(newValue));

        createdAtProperty.set(identity.getCreatedAt());
        createdAtProperty.addListener((observable, oldValue, newValue) ->  identity.setCreatedAt(newValue));

        grantMap.bind(identity.getGrants());
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public GrantMapViewModel grantMap() {
        return grantMap;
    }

    public boolean delete() {
        if(identity == null) {
            return false;
        }
        return identitiesRepo.remove(identity);
    }

}
