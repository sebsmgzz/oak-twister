package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.viewmodels.ErrorViewModel;
import com.oaktwister.app.viewmodels.models.grants.GrantMapViewModel;
import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.AccountsRepo;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.infrastructure.repos.PlatformsRepo;
import com.oaktwister.app.utils.extensions.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountViewModel extends ErrorViewModel {

    // Services
    private final AccountsRepo accountsRepo;
    private final PlatformsRepo platformsRepo;
    private final IdentitiesRepo identitiesRepo;
    private final Logger logger;

    // Other view models
    private final IdentityViewModel identityViewModel;
    private final PlatformViewModel platformViewModel;
    private final GrantMapViewModel grantMapViewModel;

    // Properties
    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleObjectProperty<UUID> platformIdProperty;
    private final SimpleObjectProperty<UUID> identityIdProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public AccountViewModel(ViewModelFactory viewModelFactory, AccountsRepo accountsRepo,
                            PlatformsRepo platformsRepo, IdentitiesRepo identitiesRepo, Logger logger) {
        this.accountsRepo = accountsRepo;
        this.platformsRepo = platformsRepo;
        this.identitiesRepo = identitiesRepo;
        this.logger = logger;
        identityViewModel = viewModelFactory.identity();
        platformViewModel = viewModelFactory.platform();
        grantMapViewModel = viewModelFactory.grantMap();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        platformIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        identityIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public boolean insert() {
        try {
            Account account = getAccount();
            accountsRepo.add(account);
            return false;
        } catch (InvalidSessionPropertyException | IOException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public boolean update() {
        try {
            Account account = getAccount();
            accountsRepo.update(account);
            return false;
        } catch (InvalidSessionPropertyException | IOException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public boolean delete() {
        try {
            Account account = getAccount();
            accountsRepo.remove(account);
            return false;
        } catch (InvalidSessionPropertyException | IOException ex) {
            logger.error(ex);
            setError(ex);
            return false;
        }
    }

    public void setAccount(Account account) throws IOException, InvalidSessionPropertyException {
        idProperty.set(account.getId());
        UUID platformId = account.getPlatformId();
        platformIdProperty.set(platformId);
        platformViewModel.setPlatform(platformsRepo.findById(platformId));
        if(account.hasIdentity()) {
            UUID identityId = account.getIdentityId();
            identityIdProperty.set(identityId);
            identityViewModel.setIdentity(identitiesRepo.findById(identityId));
        }
        createdAtProperty.set(account.getCreatedAt());
        grantMapViewModel.setGrantMap(account.getGrants());
    }

    public Account getAccount() {
        UUID id = idProperty.get();
        UUID platformId = platformIdProperty.get();
        UUID identityId = identityIdProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        return new Account(id, platformId, identityId, createdAt);
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
    public UUID getId() {
        return idProperty().get();
    }

    public ReadOnlyObjectProperty<UUID> platformIdProperty() {
        return platformIdProperty;
    }
    public UUID getPlatformId() {
        return platformIdProperty().get();
    }

    public ReadOnlyObjectProperty<UUID> identityIdProperty() {
        return identityIdProperty;
    }
    public UUID getIdentityId() {
        return identityIdProperty().get();
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty().get();
    }

}
