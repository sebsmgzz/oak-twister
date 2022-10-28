package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.Account;
import com.oaktwister.events.DeleteAccountEvent;
import com.oaktwister.models.Identity;
import com.oaktwister.models.Platform;
import com.oaktwister.models.grants.GrantMap;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.utils.extensions.UUIDUtil;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountViewModel {

    private final AccountsRepo accountsRepo;
    private final PlatformsRepo platformsRepo;
    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    private final IdentityViewModel identityViewModel;
    private final PlatformViewModel platformViewModel;
    private final GrantMapViewModel grantMapViewModel;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<UUID> platformIdProperty;
    private final SimpleObjectProperty<UUID> identityIdProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<EventHandler<DeleteAccountEvent>> onDeleteAccountProperty;

    public AccountViewModel(ViewModelFactory viewModelFactory, AccountsRepo accountsRepo,
                            PlatformsRepo platformsRepo, IdentitiesRepo identitiesRepo, Logger logger) {
        this.accountsRepo = accountsRepo;
        this.platformsRepo = platformsRepo;
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        identityViewModel = viewModelFactory.getIdentityViewModel();
        platformViewModel = viewModelFactory.getPlatformViewModel();
        grantMapViewModel = viewModelFactory.getGrantMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        platformIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        identityIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteAccountProperty = new SimpleObjectProperty<>();
    }

    public void setAccount(Account account) {
        UUID id = account.getId();
        idProperty.set(id);
        UUID platformId = account.getPlatformId();
        platformIdProperty.set(platformId);
        Platform platform = platformsRepo.findById(platformId);
        platformViewModel.setPlatform(platform);
        if(account.hasIdentity()) {
            UUID identityId = account.getIdentityId();
            identityIdProperty.set(identityId);
            Identity identity = identitiesRepo.findById(identityId);
            identityViewModel.setIdentity(identity);
        }
        LocalDateTime createdAt = account.getCreatedAt();
        createdAtProperty.set(createdAt);
        GrantMap grantMap = account.getGrants();
        grantMapViewModel.setGrantMap(grantMap);
    }

    public Account getAccount() {
        UUID id = idProperty.get();
        UUID platformId = platformIdProperty.get();
        UUID identityId = identityIdProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        Account account = new Account(id, platformId, identityId, createdAt);
        /*
        ListProperty<GrantViewModel<?>> grantViewModels = grantMapViewModel.grantsProperty();
        for(GrantViewModel<?> grantViewModel : grantViewModels) {
            Grant<?> grant = grantViewModel.getGrant();
            account.getGrants().add(grant);
        }
        */
        return account;
    }

    public IdentityViewModel identity() {
        return identityViewModel;
    }

    public PlatformViewModel platform() {
        return platformViewModel;
    }

    public GrantMapViewModel grantMap() {
        return grantMapViewModel;
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public ReadOnlyObjectProperty<UUID> platformIdProperty() {
        return platformIdProperty;
    }

    public ReadOnlyObjectProperty<UUID> identityIdProperty() {
        return identityIdProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public boolean delete() {
        Account account = getAccount();
        boolean deleted = accountsRepo.remove(account);
        if(!deleted) {
            logger.error("Failed to delete account %s", account.getId());
        }
        return deleted;
    }

}
