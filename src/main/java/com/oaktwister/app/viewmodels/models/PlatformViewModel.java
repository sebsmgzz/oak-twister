package com.oaktwister.app.viewmodels.models;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.Platform;
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
import java.time.LocalDateTime;
import java.util.UUID;

public class PlatformViewModel {

    private final PlatformsRepo platformsRepo;
    private final ImagesRepo imagesRepo;
    private final Logger logger;

    private final ClaimMapViewModel claimMapViewModel;
    private final SimpleObjectProperty<Exception> errorProperty;

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
        errorProperty = new SimpleObjectProperty<>();
        idProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        nameProperty = new SimpleStringProperty();
        imageIdProperty = new SimpleObjectProperty<>(UUIDUtil.empty());
        imageProperty = new SimpleObjectProperty<>();
        urlProperty = new SimpleStringProperty();
        createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
    }

    public boolean delete() {
        try {
            Platform platform = getPlatform();
            return platformsRepo.remove(platform);
        } catch (UnknownGrantTypeException ex) {
            errorProperty.set(ex);
            return false;
        }
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

    public ClaimMapViewModel claimMap() {
        return claimMapViewModel;
    }

    public ReadOnlyObjectProperty<Exception> errorProperty() {
        return errorProperty;
    }
    public Exception getError() {
        return errorProperty().get();
    }
    public void clearError() {
        errorProperty.set(null);
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
