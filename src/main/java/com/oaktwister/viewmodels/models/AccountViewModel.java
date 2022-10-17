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

    public SimpleObjectProperty<UUID> idProperty() {
        return id;
    }

    public UUID getId() {
        return idProperty().get();
    }

    public void setId(UUID id) {
        idProperty().set(id);
    }

    public SimpleObjectProperty<UUID> platformIdProperty() {
        return platformId;
    }

    public UUID getPlatformId() {
        return platformIdProperty().get();
    }

    public void setPlatformId(UUID id) {
        platformIdProperty().set(id);
    }

    public SimpleObjectProperty<UUID> identityIdProperty() {
        return identityId;
    }

    public UUID getIdentityId() {
        return identityIdProperty().get();
    }

    public void setIdentityId(UUID id) {
        identityIdProperty().set(id);
    }

    public SimpleObjectProperty<LocalDateTime> createAtProperty() {
        return createAt;
    }

    public LocalDateTime getCreatedAt() {
        return createAtProperty().get();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        createAtProperty().set(createdAt);
    }

}
