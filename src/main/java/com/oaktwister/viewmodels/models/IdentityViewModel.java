package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.Identity;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.events.DeleteIdentityEvent;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;
    private final LocalDateTimeUtil localDateTimeUtil;
    private final Logger logger;

    private Identity identity;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty;
    private final GrantMapViewModel grantMap;

    public IdentityViewModel(ViewModelFactory viewModelFactory, IdentitiesRepo identitiesRepo,
                             UUIDUtil uuidUtil, LocalDateTimeUtil localDateTimeUtil, Logger logger) {
        this.identitiesRepo = identitiesRepo;
        this.localDateTimeUtil = localDateTimeUtil;
        this.logger = logger;
        idProperty = new SimpleObjectProperty<>(uuidUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteIdentityProperty = new SimpleObjectProperty<>();
        grantMap = viewModelFactory.getGrantMapViewModel();
    }

    public void bind(Identity identity) {
        this.identity = identity;

        idProperty.set(identity.getId());
        idProperty.addListener((observable, oldValue, newValue) -> this.identity.setId(newValue));

        createdAtProperty.set(identity.getCreatedAt());
        createdAtProperty.addListener((observable, oldValue, newValue) ->  this.identity.setCreatedAt(newValue));

        grantMap.bind(identity.getGrants());
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public SimpleObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty() {
        return onDeleteIdentityProperty;
    }

    public GrantMapViewModel grants() {
        return grantMap;
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

    public String formatDate(LocalDateTime dateTime) {
        return localDateTimeUtil.toDefault(dateTime);
    }

}
