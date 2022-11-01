package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.Identity;
import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.utils.extensions.UUIDUtil;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private final GrantMapViewModel grantMapViewModel;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public IdentityViewModel(ViewModelFactory viewModelFactory, IdentitiesRepo identitiesRepo, Logger logger) {
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        grantMapViewModel = viewModelFactory.grantMap();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public void setIdentity(Identity identity) {
        UUID id = identity.getId();
        idProperty.set(id);
        LocalDateTime createdAt = identity.getCreatedAt();
        createdAtProperty.set(createdAt);
        GrantMap grantMap = identity.getGrantMap();
        grantMapViewModel.setGrantMap(grantMap);
    }

    public Identity getIdentity() {
        UUID id = idProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        Identity identity = new Identity(id, createdAt);
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
