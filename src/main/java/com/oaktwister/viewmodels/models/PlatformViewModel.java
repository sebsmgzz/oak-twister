package com.oaktwister.viewmodels.models;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.models.Platform;
import com.oaktwister.models.claims.Claim;
import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.utils.extensions.UUIDUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlatformViewModel {

    private final PlatformsRepo platformsRepo;
    private final ImagesRepo imagesRepo;
    private final Logger logger;

    private final SimpleObjectProperty<UUID> idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleObjectProperty<UUID> imageIdProperty;
    private final SimpleObjectProperty<Image> imageProperty;
    private final SimpleStringProperty urlProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    private final ClaimMapViewModel claimMapViewModel;

    public PlatformViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo,
                             ImagesRepo imagesRepo, Logger logger) {
        this.platformsRepo = platformsRepo;
        this.imagesRepo = imagesRepo;
        this.logger = logger;
        claimMapViewModel = viewModelFactory.getClaimMapViewModel();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        nameProperty = new SimpleStringProperty();
        imageIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        imageProperty = new SimpleObjectProperty<>();
        urlProperty = new SimpleStringProperty();
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public void setPlatform(Platform platform) {
        UUID id = platform.getId();
        idProperty.set(id);
        String name = platform.getName();
        nameProperty.set(name);
        UUID imageId = platform.getImageId();
        imageIdProperty.set(imageId);
        Image image = imagesRepo.findById(imageId);
        imageProperty.set(image);
        String url = platform.getUrl();
        urlProperty.set(url);
        LocalDateTime createdAt = platform.getCreatedAt();
        createdAtProperty.set(createdAt);
        ClaimMap claimMap = platform.getClaims();
        claimMapViewModel.setClaimMap(claimMap);
    }

    public Platform getPlatform() throws UnknownGrantTypeException {
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

    public ReadOnlyObjectProperty<UUID> idProperty() {
        return idProperty;
    }

    public SimpleStringProperty nameProperty() {
        return nameProperty;
    }

    public ReadOnlyObjectProperty<UUID> imageIdProperty() {
        return imageIdProperty;
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

    public ClaimMapViewModel claimMap() {
        return claimMapViewModel;
    }

    public boolean delete() {
        try {
            Platform platform = getPlatform();
            return platformsRepo.remove(platform);
        } catch (UnknownGrantTypeException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
