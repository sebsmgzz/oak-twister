package com.oaktwister.viewmodels.collections;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.models.Platform;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class PlatformsViewModel {

    private final ViewModelFactory viewModelFactory;
    private final PlatformsRepo platformsRepo;
    private final Logger logger;

    private final SimpleListProperty<PlatformViewModel> platformsProperty;

    public PlatformsViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo, Logger logger) {
        this.viewModelFactory = viewModelFactory;
        this.platformsRepo = platformsRepo;
        this.logger = logger;
        platformsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ReadOnlyListProperty<PlatformViewModel> platformsProperty() {
        return platformsProperty;
    }

    public boolean addPlatform(String name, UUID image, String url) {
        logger.debug("Adding platform");
        Platform platform = new Platform(name, image, url);
        boolean success = platformsRepo.add(platform);
        if(success) {
            PlatformViewModel platformViewModel = viewModelFactory.platform();
            this.platformsProperty.add(platformViewModel);
            platformViewModel.setPlatform(platform);
        }
        return success;
    }

    public void load() {
        logger.debug("Loading platforms");
        List<Platform> platforms = platformsRepo.findAll();
        for(Platform platform : platforms) {
            PlatformViewModel platformViewModel = viewModelFactory.platform();
            platformViewModel.setPlatform(platform);
            this.platformsProperty.add(platformViewModel);
        }
        logger.debug("Loaded %s platforms", platforms.size());
    }

    public void clear() {
        ObservableList<PlatformViewModel> platformViewModels = platformsProperty.get();
        platformsProperty.removeAll(platformViewModels);
    }

}
