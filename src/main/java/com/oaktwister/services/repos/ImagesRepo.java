package com.oaktwister.services.repos;

import com.oaktwister.services.Context;
import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImagesRepo {

    public static final String LOCATION = "images";
    public static final String FILE_EXTENSION = ".png";

    private final Context context;

    public ImagesRepo(Context context) {
        this.context = context;
    }

    private Path getFullRepoLocation() {
        return Paths.get(context.getDrive().getPath(), LOCATION);
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
