package com.oaktwister.viewmodels.main;

import com.oaktwister.models.Platform;
import com.oaktwister.services.PlatformRepository;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;

import java.util.List;

public class PlatformsViewModel {

    private final PlatformRepository platformRepository;
    private final SimpleListProperty<PlatformViewModel> platforms;

    public PlatformsViewModel(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void loadPlatforms() {
        List<Platform> platforms = platformRepository.findAllPlatforms();
        for(Platform platform : platforms) {
            this.platforms.add(new PlatformViewModel(platform));
        }
    }

    public SimpleListProperty<PlatformViewModel> platformsProperty() {
        return platforms;
    }

    public boolean addPlatform(String name, String image, String url) {
        Platform platform = new Platform(null, name, image, url);
        boolean success = platformRepository.addPlatform(platform);
        if(success) {
            platformsProperty().add(new PlatformViewModel(platform));
        }
        return success;
    }

}
