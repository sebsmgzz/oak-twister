package com.oaktwister.app.viewmodels.views;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.Platform;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.PlatformsRepo;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlatformsViewModel {

    private final ViewModelFactory viewModelFactory;
    private final PlatformsRepo platformsRepo;
    private final Logger logger;

    private final SimpleObjectProperty<PlatformViewModel> selectedPlatformProperty;
    private final SimpleListProperty<PlatformViewModel> platformsProperty;

    public PlatformsViewModel(ViewModelFactory viewModelFactory, PlatformsRepo platformsRepo, Logger logger) {
        this.viewModelFactory = viewModelFactory;
        this.platformsRepo = platformsRepo;
        this.logger = logger;
        selectedPlatformProperty = new SimpleObjectProperty<>();
        platformsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void load() {
        logger.debug("Loading platforms");
        List<Platform> platforms = platformsRepo.findAll();
        for(Platform platform : platforms) {
            PlatformViewModel platformViewModel = viewModelFactory.platform();
            platformViewModel.setPlatform(platform);
            addPlatform(platformViewModel);
        }
        logger.debug("Loaded %s platforms", platforms.size());
    }

    public ReadOnlyListProperty<PlatformViewModel> platformsProperty() {
        return platformsProperty;
    }
    public ObservableList<PlatformViewModel> getPlatforms() {
        return platformsProperty().get();
    }
    public boolean addPlatform(PlatformViewModel platform) {
        return platformsProperty().add(platform);
    }
    public boolean addPlatforms(PlatformViewModel... platforms) {
        return platformsProperty().addAll(platforms);
    }
    public boolean removePlatform(PlatformViewModel platform) {
        return platformsProperty().remove(platform);
    }
    public boolean removePlatforms(PlatformViewModel... platforms) {
        return platformsProperty().removeAll(platforms);
    }
    public void clearPlatforms() {
        platformsProperty().clear();
    }

    public ObjectProperty<PlatformViewModel> selectedPlatformProperty() {
        return selectedPlatformProperty;
    }
    public PlatformViewModel getSelectedPlatform() {
        return selectedPlatformProperty().get();
    }
    public void setSelectedPlatform(PlatformViewModel value) {
        selectedPlatformProperty().set(value);
    }

}
