package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Account;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountViewModel {

    private final Account account;
    private final SimpleObjectProperty<UUID> id;
    private final SimpleObjectProperty<UUID> platformId;
    private final SimpleObjectProperty<UUID> identityId;
    private final SimpleObjectProperty<LocalDateTime> createAt;

    public AccountViewModel(Account account) {
        this.account = account;
        this.id = new SimpleObjectProperty<>(account.getId());
        this.platformId = new SimpleObjectProperty<>(account.getPlatformId());
        this.identityId = new SimpleObjectProperty<>(account.getIdentityId());
        this.createAt = new SimpleObjectProperty<>(account.getCreatedAt());
        initialize();
    }

    public void initialize() {
        id.addListener((observable, oldValue, newValue) -> account.setId(newValue));
        platformId.addListener((observable, oldValue, newValue) -> account.setPlatformId(newValue));
        identityId.addListener((observable, oldValue, newValue) -> account.setIdentityId(newValue));
        createAt.addListener((observable, oldValue, newValue) -> account.setCreatedAt(newValue));
    }

    public SimpleObjectProperty<UUID> id() {
        return id;
    }

    public SimpleObjectProperty<UUID> platformId() {
        return platformId;
    }

    public SimpleObjectProperty<UUID> identityId() {
        return identityId;
    }

    public SimpleObjectProperty<LocalDateTime> createAt() {
        return createAt;
    }

}
