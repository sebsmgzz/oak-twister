package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.viewmodels.ErrorViewModel;
import com.oaktwister.app.viewmodels.models.claims.ClaimMapViewModel;
import com.oaktwister.app.viewmodels.models.claims.ClaimViewModel;
import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.platforms.Platform;
import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.ClaimMap;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.ImagesRepo;
import com.oaktwister.infrastructure.repos.PlatformsRepo;
import com.oaktwister.app.utils.extensions.UUIDUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlatformViewModel extends ErrorViewModel {

    // Services
    private final PlatformsRepo platformsRepo;
    private final ImagesRepo imagesRepo;
    private final Logger logger;

    // Other view model
    private final ClaimMapViewModel claimMapViewModel;

    // Properties
    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<UUID> imageIdProperty;
    private final SimpleObjectProperty<Image> imageProperty;
    private final SimpleStringProperty urlProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public PlatformViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo,
                             ImagesRepo imagesRepo, Logger logger) {
        this.platformsRepo = platformsRepo;
        this.imagesRepo = imagesRepo;
        this.logger = logger;
        claimMapViewModel = viewModelFactory.claimMap();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        nameProperty = new SimpleStringProperty();
        imageIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        imageProperty = new SimpleObjectProperty<>();
        urlProperty = new SimpleStringProperty();
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public boolean insert() {
        try {
            Platform platform = getPlatform();
            platformsRepo.add(platform);
            return true;
        } catch (InvalidSessionPropertyException | IOException ex) {
            logger.error(ex, ex.getMessage());
            setError(ex);
            return false;
        }
    }

    public boolean update() {
        try {
            Platform platform = getPlatform();
            platformsRepo.update(platform);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            setError(ex);
            return false;
        }
    }

    public boolean delete() {
        try {
            Platform platform = getPlatform();
            platformsRepo.remove(platform);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            setError(ex);
            return false;
        }
    }

    public void copy(PlatformViewModel source) {
        idProperty.set(source.getId());
        nameProperty.set(source.getName());
        imageProperty.set(source.getImage());
        imageIdProperty.set(source.getImageId());
        urlProperty.set(source.getUrl());
        createdAtProperty.set(source.getCreatedAt());
        claimMapViewModel.copy(source.claimMap());
    }

    public void setPlatform(Platform platform) throws InvalidSessionPropertyException {
        idProperty.set(platform.getId());
        nameProperty.set(platform.getName());
        UUID imageId = platform.getImageId();
        imageIdProperty.set(imageId);
        imageProperty.set(imagesRepo.findById(imageId));
        urlProperty.set(platform.getUrl());
        createdAtProperty.set(platform.getCreatedAt());
        claimMapViewModel.setClaimMap(platform.getClaims());
    }

    public Platform getPlatform() {
        UUID id = idProperty.get();
        String name = nameProperty.get();
        UUID imageId = imageIdProperty.get();
        String url = urlProperty.get();
        LocalDateTime createdAt = createdAtProperty.get();
        Platform platform = new Platform(id, name, imageId, url, createdAt);
        ClaimMap claims = platform.getClaims();
        ObservableList<ClaimViewModel> claimViewModels = claimMapViewModel.claimsProperty().get();
        for(ClaimViewModel claimViewModel : claimViewModels) {
            Claim claim = claimViewModel.getClaim();
            claims.add(claim);
        }
        return platform;
    }

    public ClaimMapViewModel claimMap() {
        return claimMapViewModel;
    }

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }
    public UUID getId() {
        return idProperty().get();
    }

    public SimpleStringProperty nameProperty() {
        return nameProperty;
    }
    public String getName() {
        return nameProperty().get();
    }
    public void setName(String value) {
        nameProperty().set(value);
    }

    public ReadOnlyObjectProperty<UUID> imageIdProperty() {
        return imageIdProperty;
    }
    public UUID getImageId() {
        return imageIdProperty().get();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }
    public Image getImage() {
        return imageProperty().get();
    }
    public void setImage(Image value) {
        imageProperty().set(value);
    }

    public StringProperty urlProperty() {
        return urlProperty;
    }
    public String getUrl() {
        return urlProperty().get();
    }
    public void setUrl(String value) {
        urlProperty().set(value);
    }

    public ReadOnlyObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }
    public LocalDateTime getCreatedAt() {
        return createdAtProperty().get();
    }


}
