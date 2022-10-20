package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.util.UUIDUtil;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlatformViewModel {

    private final ImagesRepo imagesRepo;

    private Platform platform;

    private final SimpleObjectProperty<UUID> id;
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Image> image;
    private final SimpleStringProperty url;
    private final SimpleObjectProperty<LocalDateTime> createdAt;
    private final ClaimMapViewModel claims;

    public PlatformViewModel(ImagesRepo imagesRepo, UUIDUtil uuidUtil) {
        this.imagesRepo = imagesRepo;
        id = new SimpleObjectProperty<>(uuidUtil.empty());
        name = new SimpleStringProperty(null);
        image = new SimpleObjectProperty<>(null);
        url = new SimpleStringProperty(null);
        createdAt = new SimpleObjectProperty<>(LocalDateTime.MIN);
        claims = new ClaimMapViewModel();
    }

    public void bind(Platform platform) {
        this.platform = platform;

        id.set(platform.getId());
        id.addListener((observable, oldValue, newValue) -> this.platform.setId(newValue));

        name.set(platform.getName());
        name.addListener((observable, oldValue, newValue) -> this.platform.setName(newValue));

        image.set(imagesRepo.findById(platform.getImageId()));
        image.addListener((observable, oldValue, newValue) -> this.platform.setImageId(imagesRepo.add(newValue)));

        url.set(platform.getUrl());
        url.addListener((observable, oldValue, newValue) -> this.platform.setUrl(newValue));

        createdAt.set(platform.getCreatedAt());
        createdAt.addListener((observable, oldValue, newValue) -> this.platform.setCreatedAt(newValue));

        claims.bind(platform.getClaims());
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    public ClaimMapViewModel claims() {
        return claims;
    }

}
