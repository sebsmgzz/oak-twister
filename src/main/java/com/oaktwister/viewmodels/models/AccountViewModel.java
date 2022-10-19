package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountViewModel {

    private Account account;

    private final GrantMapViewModel grants;
    private final SimpleObjectProperty<UUID> id;
    private final SimpleObjectProperty<UUID> platformId;
    private final SimpleObjectProperty<UUID> identityId;
    private final SimpleObjectProperty<LocalDateTime> createdAt;

    public AccountViewModel() {
        grants = new GrantMapViewModel();
        this.id = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        this.platformId = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        this.identityId = new SimpleObjectProperty<>(UUIDStringConverter.empty());
        this.createdAt = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public void bind(Account account) {
        this.account = account;

        id.set(account.getId());
        id.addListener((observable, oldValue, newValue) -> account.setId(newValue));

        platformId.set(account.getPlatformId());
        platformId.addListener((observable, oldValue, newValue) -> account.setPlatformId(newValue));

        identityId.set(account.getIdentityId());
        identityId.addListener((observable, oldValue, newValue) -> account.setIdentityId(newValue));

        createdAt.set(account.getCreatedAt());
        createdAt.addListener((observable, oldValue, newValue) -> account.setCreatedAt(newValue));

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

    public GrantMapViewModel grants() {
        return grants;
    }

}
