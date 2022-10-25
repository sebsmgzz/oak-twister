package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.events.DeletePlatformEvent;
import com.oaktwister.models.Platform;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlatformViewModel {

    private final PlatformsRepo platformsRepo;
    private final ImagesRepo imagesRepo;
    private final Logger logger;

    private final SimpleObjectProperty<UUID> id;
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Image> image;
    private final SimpleStringProperty url;
    private final SimpleObjectProperty<LocalDateTime> createdAt;
    private final SimpleObjectProperty<EventHandler<DeletePlatformEvent>> onDeleteAccountProperty;
    private final ClaimMapViewModel claims;

    private Platform platform;

    public PlatformViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo,
                             ImagesRepo imagesRepo, Logger logger) {
        this.platformsRepo = platformsRepo;
        this.imagesRepo = imagesRepo;
        this.logger = logger;
        id = new SimpleObjectProperty<>(UUIDUtil.empty());
        name = new SimpleStringProperty(null);
        image = new SimpleObjectProperty<>(null);
        url = new SimpleStringProperty(null);
        createdAt = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteAccountProperty = new SimpleObjectProperty<>();
        claims = viewModelFactory.getClaimMapViewModel();
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

    public SimpleObjectProperty<EventHandler<DeletePlatformEvent>> onDeleteAccountProperty() {
        return onDeleteAccountProperty;
    }

    public ClaimMapViewModel claims() {
        return claims;
    }

    public boolean delete() {
        if(platform == null) {
            logger.warn("Attempted to delete a platform without having it set beforehand");
            return false;
        }
        DeletePlatformEvent event = new DeletePlatformEvent(this);
        EventHandler<DeletePlatformEvent> eventHandler = onDeleteAccountProperty.get();
        if(eventHandler != null) {
            eventHandler.handle(event);
        }
        if(event.isCanceled()) {
            logger.info("Delete platform event cancelled");
            return false;
        } else {
            boolean deleted = platformsRepo.remove(platform);
            if(!deleted) {
                logger.error("Failed to delete platform %s", platform.getId());
            }
            return deleted;
        }
    }

}
