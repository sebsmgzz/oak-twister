package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.services.configs.Session;
import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImagesRepo {

    public static final String DIRECTORY_NAME = "images";
    public static final String FILE_EXTENSION = ".png";

    private final Session session;

    public ImagesRepo(Session session) {
        this.session = session;
    }

    private Path getRepoAbsolutePath() throws InvalidSessionPropertyException {
        String path = session.getDrive().getPath();
        return Paths.get(path, DIRECTORY_NAME);
    }

    private Path getEntityAbsolutePath(UUID id) throws InvalidSessionPropertyException {
        String fileName = id + FILE_EXTENSION;
        String fullRepoLocation = getRepoAbsolutePath().toString();
        return Paths.get(fullRepoLocation, fileName);
    }

    public Image findById(UUID id) throws InvalidSessionPropertyException {
        String fileLocation = getEntityAbsolutePath(id).toString();
        return new Image(fileLocation);
    }
    public Image tryFindById(UUID id) {
        try {
            return findById(id);
        } catch (InvalidSessionPropertyException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void add(Image image) {
        // TODO
    }
    public boolean tryAdd(Image image) {
        add(image);
        return true;
    }

    public void remove(Image image) {
        // TODO
    }
    public boolean tryRemove(Image image) {
        remove(image);
        return true;
    }

}
