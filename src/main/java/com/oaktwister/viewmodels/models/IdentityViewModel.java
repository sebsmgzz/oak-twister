package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.Identity;
import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.events.DeleteIdentityEvent;
import com.oaktwister.utils.extensions.UUIDUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private Identity identity;
    private final GrantMapViewModel grantMapViewModel;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty;

    public IdentityViewModel(ViewModelFactory viewModelFactory, IdentitiesRepo identitiesRepo, Logger logger) {
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        grantMapViewModel = viewModelFactory.getGrantMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteIdentityProperty = new SimpleObjectProperty<>();
    }

    public void setIdentity(Identity identity) {
        if(this.identity != null) {
            throw new RuntimeException("Identity has already been set");
        }
        this.identity = identity;

        UUID id = identity.getId();
        idProperty.set(id);
        idProperty.addListener((observable, oldValue, newValue) -> {
            this.identity.setId(newValue);
        });

        LocalDateTime createdAt = identity.getCreatedAt();
        createdAtProperty.set(createdAt);
        createdAtProperty.addListener((observable, oldValue, newValue) -> {
            this.identity.setCreatedAt(newValue);
        });

        GrantMap grantMap = identity.getGrantMap();
        grantMapViewModel.setGrantMap(grantMap);
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

    public ObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty() {
        return onDeleteIdentityProperty;
    }

    public boolean delete() {
        if(identity == null) {
            logger.warn("Attempted to delete identity without having it set beforehand");
            return false;
        }
        DeleteIdentityEvent event = new DeleteIdentityEvent(this);
        EventHandler<DeleteIdentityEvent> eventHandler = onDeleteIdentityProperty.get();
        if(eventHandler != null) {
            eventHandler.handle(event);
        }
        if(event.isCanceled()) {
            logger.info("Delete identity event cancelled");
            return false;
        } else {
            boolean deleted = identitiesRepo.remove(identity);
            if(!deleted) {
                logger.error("Failed to delete identity %s", identity.getId());
            }
            return deleted;
        }
    }

}
