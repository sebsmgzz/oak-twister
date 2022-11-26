package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.viewmodels.ErrorViewModel;
import com.oaktwister.app.viewmodels.models.grants.GrantMapViewModel;
import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.identities.Identity;
import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.app.utils.extensions.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel extends ErrorViewModel {

    // Services
    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    // Other view models
    private final GrantMapViewModel grantMapViewModel;

    // Properties
    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public IdentityViewModel(ViewModelFactory viewModelFactory, IdentitiesRepo identitiesRepo, Logger logger) {
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        grantMapViewModel = viewModelFactory.grantMap();
        nameProperty = new SimpleStringProperty();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public boolean insert() {
        try {
            Identity identity = getIdentity();
            identitiesRepo.add(identity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public boolean update() {
        try {
            Identity identity = getIdentity();
            identitiesRepo.update(identity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public boolean delete() {
        try {
            Identity identity = getIdentity();
            identitiesRepo.remove(identity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public void setIdentity(Identity identity) {
        idProperty.set(identity.getId());
        nameProperty.set(identity.getName());
        createdAtProperty.set(identity.getCreatedAt());
        grantMapViewModel.setGrantMap(identity.getGrantMap());
    }

    public Identity getIdentity() {
        UUID id = idProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        Identity identity = new Identity(id, createdAt);
        identity.setName(nameProperty.get());
        GrantMap grantMap = identity.getGrantMap();
        for(Grant<?> grant : grantMapViewModel.getGrantMap()) {
            grantMap.add(grant);
        }
        return identity;
    }

    public GrantMapViewModel grantMap() {
        return grantMapViewModel;
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

}
