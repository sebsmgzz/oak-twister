package com.oaktwister.viewmodels.collections;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.beans.property.ReadOnlyListProperty;
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

    public ReadOnlyListProperty<PlatformViewModel> platformsProperty() {
        return platforms;
    }

    public void loadPlatforms() {
        logger.debug("Loading platforms");
        List<Platform> platforms = platformsRepo.findAll();
        for(Platform platform : platforms) {
            PlatformViewModel platformViewModel = new PlatformViewModel(imagesRepo);
            this.platforms.add(platformViewModel);
            platformViewModel.bind(platform);
        }
        logger.debug("Loaded %s platforms", platforms.size());
    }

    public boolean addPlatform(String name, UUID image, String url) {
        logger.debug("Adding platform");
        Platform platform = new Platform(name, image, url);
        boolean success = platformsRepo.add(platform);
        if(success) {
            PlatformViewModel platformViewModel = new PlatformViewModel(imagesRepo);
            this.platforms.add(platformViewModel);
            platformViewModel.bind(platform);
        }
        return success;
    }

}
