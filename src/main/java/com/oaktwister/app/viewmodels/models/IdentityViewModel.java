package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.Identity;
import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.app.utils.extensions.UUIDUtil;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private final GrantMapViewModel grantMapViewModel;

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

    public void setIdentity(Identity identity) {
        UUID id = identity.getId();
        idProperty.set(id);
        String name = identity.getName();
        nameProperty.set(name);
        LocalDateTime createdAt = identity.getCreatedAt();
        createdAtProperty.set(createdAt);
        GrantMap grantMap = identity.getGrantMap();
        grantMapViewModel.setGrantMap(grantMap);
    }

    public Identity getIdentity() {
        UUID id = idProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        String name = nameProperty.get();
        Identity identity = new Identity(id, createdAt);
        identity.setName(name);
        GrantMap grantMap = identity.getGrantMap();
        /*
        ObservableList<GrantViewModel<?>> grantViewModels = grantMapViewModel.grantsProperty().get();
        for(GrantViewModel<?> grantViewModel : grantViewModels) {
            Grant<?> grant = grantViewModel.getGrant();
            grantMap.add(grant);
        }
        */
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


    public boolean delete() {
        Identity identity = getIdentity();
        boolean deleted = identitiesRepo.remove(identity);
        if(!deleted) {
            logger.error("Failed to delete identity %s", identity.getId());
        }
        return deleted;
    }

}
