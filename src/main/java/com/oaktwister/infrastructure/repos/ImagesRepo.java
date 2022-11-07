package com.oaktwister.infrastructure.repos;

import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.logging.Logger;
import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImagesRepo {

    public static final String LOCATION = "images";
    public static final String FILE_EXTENSION = ".png";

    private final SessionSettings sessionSettings;
    private final Logger logger;

    public ImagesRepo(SessionSettings sessionSettings, Logger logger) {
        this.sessionSettings = sessionSettings;
        this.logger = logger;
    }

    private Path getFullRepoLocation() {
        String path = sessionSettings.getDrive().getPath();
        return Paths.get(path, LOCATION);
    }

    public Path getImageLocation(UUID id) {
        String fileName = id + FILE_EXTENSION;
        return Paths.get(getFullRepoLocation().toString(), fileName);
    }

    public Image findById(UUID id) {
        try {
            String fileLocation = getImageLocation(id).toString();
            return new Image(fileLocation);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UUID add(Image image) {
        // TODO
        return UUID.randomUUID();
    }

}
