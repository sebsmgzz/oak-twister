package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.aggregators.Account;
import com.oaktwister.models.events.DeleteAccountEvent;
import com.oaktwister.models.events.DeleteIdentityEvent;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.services.util.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountViewModel {

    private final AccountsRepo accountsRepo;
    private final PlatformsRepo platformsRepo;
    private final IdentitiesRepo identitiesRepo;

    private final SimpleObjectProperty<UUID> id;
    private final SimpleObjectProperty<UUID> platformId;
    private final SimpleObjectProperty<UUID> identityId;
    private final SimpleObjectProperty<LocalDateTime> createdAt;
    private final SimpleObjectProperty<EventHandler<DeleteAccountEvent>> onDeleteAccountProperty;
    private final IdentityViewModel identity;
    private final PlatformViewModel platform;
    private final GrantMapViewModel grants;

    private Account account;

    public AccountViewModel(ViewModelFactory viewModelFactory, AccountsRepo accountsRepo,
                            PlatformsRepo platformsRepo, IdentitiesRepo identitiesRepo, UUIDUtil uuidUtil) {
        this.accountsRepo = accountsRepo;
        this.platformsRepo = platformsRepo;
        this.identitiesRepo = identitiesRepo;
        this.id = new SimpleObjectProperty<>(uuidUtil.empty());
        this.platformId = new SimpleObjectProperty<>(uuidUtil.empty());
        this.identityId = new SimpleObjectProperty<>(uuidUtil.empty());
        this.createdAt = new SimpleObjectProperty<>(LocalDateTime.MIN);
        this.onDeleteAccountProperty = new SimpleObjectProperty<>();
        identity = viewModelFactory.getIdentityViewModel();
        platform = viewModelFactory.getPlatformViewModel();
        grants = viewModelFactory.getGrantMapViewModel();
    }

    public void bind(Account account) {
        this.account = account;

        id.set(account.getId());
        id.addListener((observable, oldValue, newValue) -> this.account.setId(newValue));

        platformId.set(account.getPlatformId());
        platformId.addListener((observable, oldValue, newValue) -> this.account.setPlatformId(newValue));

        identityId.set(account.getIdentityId());
        identityId.addListener((observable, oldValue, newValue) -> this.account.setIdentityId(newValue));

        createdAt.set(account.getCreatedAt());
        createdAt.addListener((observable, oldValue, newValue) -> this.account.setCreatedAt(newValue));

        if(account.hasIdentity()) {
            identity.bind(identitiesRepo.findById(account.getIdentityId()));
        }
        platform.bind(platformsRepo.findById(account.getPlatformId()));
        grants.bind(account.getGrants());
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return id;
    }

    public ReadOnlyObjectProperty<UUID> platformIdProperty() {
        return platformId;
    }

    public ReadOnlyObjectProperty<UUID> identityIdProperty() {
        return identityId;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    public SimpleObjectProperty<EventHandler<DeleteAccountEvent>> onDeleteAccountProperty() {
        return onDeleteAccountProperty;
    }

    public IdentityViewModel identity() {
        return identity;
    }

    public PlatformViewModel platform() {
        return platform;
    }

    public GrantMapViewModel grants() {
        return grants;
    }

    public boolean delete() {
        if(account == null) {
            // TODO: Throw exception?
            return false;
        }
        DeleteAccountEvent event = new DeleteAccountEvent(this);
        EventHandler<DeleteAccountEvent> eventHandler = onDeleteAccountProperty.get();
        if(eventHandler != null) {
            eventHandler.handle(event);
        }
        if(event.isCanceled()) {
            return false;
        }
        return accountsRepo.remove(account);
    }

}
