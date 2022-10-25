package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.events.DeletePlatformEvent;
import com.oaktwister.models.Platform;
import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
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

    private Platform platform;
    private final ClaimMapViewModel claimMapViewModel;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<Image> imageProperty;
    private final SimpleStringProperty urlProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;
    private final SimpleObjectProperty<EventHandler<DeletePlatformEvent>> onDeleteAccountProperty;

    public PlatformViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo,
                             ImagesRepo imagesRepo, Logger logger) {
        this.platformsRepo = platformsRepo;
        this.imagesRepo = imagesRepo;
        this.logger = logger;
        claimMapViewModel = viewModelFactory.getClaimMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        nameProperty = new SimpleStringProperty();
        imageProperty = new SimpleObjectProperty<>();
        urlProperty = new SimpleStringProperty();
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        onDeleteAccountProperty = new SimpleObjectProperty<>();
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;

        UUID id = platform.getId();
        idProperty.set(id);
        idProperty.addListener((observable, oldValue, newValue) -> {
            this.platform.setId(newValue);
        });

        String name = platform.getName();
        nameProperty.set(name);
        nameProperty.addListener((observable, oldValue, newValue) -> {
            this.platform.setName(newValue);
        });

        UUID imageId = platform.getImageId();
        Image image = imagesRepo.findById(imageId);
        imageProperty.set(image);
        imageProperty.addListener((observable, oldValue, newValue) -> {
            UUID newImageId = imagesRepo.add(newValue);
            this.platform.setImageId(newImageId);
        });

        String url = platform.getUrl();
        urlProperty.set(url);
        urlProperty.addListener((observable, oldValue, newValue) -> {
            this.platform.setUrl(newValue);
        });

        LocalDateTime createdAt = platform.getCreatedAt();
        createdAtProperty.set(createdAt);
        createdAtProperty.addListener((observable, oldValue, newValue) -> {
            this.platform.setCreatedAt(newValue);
        });

        ClaimMap claimMap = platform.getClaims();
        claimMapViewModel.bind(claimMap);
    }

    public ClaimMapViewModel claims() {
        return claimMapViewModel;
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public SimpleStringProperty nameProperty() {
        return nameProperty;
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public SimpleStringProperty urlProperty() {
        return urlProperty;
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

    public SimpleObjectProperty<EventHandler<DeletePlatformEvent>> onDeleteAccountProperty() {
        return onDeleteAccountProperty;
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
