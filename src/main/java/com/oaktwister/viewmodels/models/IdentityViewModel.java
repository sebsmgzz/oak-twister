package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.events.DeleteIdentityEvent;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdentityViewModel {

    private final IdentitiesRepo identitiesRepo;

    private Identity identity;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty;
    private final GrantMapViewModel grantMap;

    public IdentityViewModel(IdentitiesRepo identitiesRepo) {
        this.identitiesRepo = identitiesRepo;
        grantMap = new GrantMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteIdentityProperty = new SimpleObjectProperty<>();
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

    public SimpleObjectProperty<EventHandler<DeleteIdentityEvent>> onDeleteIdentityProperty() {
        return onDeleteIdentityProperty;
    }

    public GrantMapViewModel grantMap() {
        return grantMap;
    }

    public boolean delete() {
        if(identity == null) {
            // TODO: Throw exception?
            return false;
        }
        DeleteIdentityEvent event = new DeleteIdentityEvent(this);
        EventHandler<DeleteIdentityEvent> eventHandler = onDeleteIdentityProperty.get();
        if(eventHandler != null) {
            eventHandler.handle(event);
        }
        if(event.isCanceled()) {
            return false;
        }
        return identitiesRepo.remove(identity);
    }

}
