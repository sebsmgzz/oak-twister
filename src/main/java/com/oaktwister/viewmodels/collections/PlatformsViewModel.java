package com.oaktwister.viewmodels.collections;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.UUID;

public class PlatformsViewModel {

    private final ImagesRepo imagesRepo;
    private final PlatformsRepo platformsRepo;
    private final Logger logger;

    private final SimpleListProperty<PlatformViewModel> platforms;

    public PlatformsViewModel(ImagesRepo imagesRepo, PlatformsRepo platformsRepo, Logger logger) {
        this.imagesRepo = imagesRepo;
        this.platformsRepo = platformsRepo;
        this.logger = logger;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void loadPlatforms() {
        logger.debug("Loading platforms");
        List<Platform> platforms = platformsRepo.findAll();
        for(Platform platform : platforms) {
            this.platforms.add(new PlatformViewModel(imagesRepo, platform));
        }
    }

    public SimpleListProperty<PlatformViewModel> platformsProperty() {
        return platforms;
    }

    public boolean addPlatform(String name, UUID image, String url) {
        logger.debug("Adding platform");
        Platform platform = new Platform(name, image, url);
        boolean success = platformsRepo.add(platform);
        if(success) {
            platformsProperty().add(new PlatformViewModel(imagesRepo, platform));
        }
        return success;
    }

}
