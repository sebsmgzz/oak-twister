package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.Context;
import com.oaktwister.services.repos.ImagesRepo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

public class PlatformViewModel {

    private final ImagesRepo imagesRepo;

    private final Platform platform;
    private final SimpleObjectProperty<UUID> id;
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Image> image;
    private final SimpleStringProperty url;

    public PlatformViewModel(ImagesRepo imagesRepo, Platform platform) {
        this.imagesRepo = imagesRepo;
        this.platform = platform;
        id = new SimpleObjectProperty<>(platform.getId());
        name = new SimpleStringProperty(platform.getName());
        image = new SimpleObjectProperty<>(imagesRepo.findById(platform.getImageId()));
        url = new SimpleStringProperty(platform.getUrl());
        initialize();
    }

    private void initialize() {
        id.addListener((observable, oldValue, newValue) -> platform.setId(newValue));
        name.addListener((observable, oldValue, newValue) -> platform.setName(newValue));
        image.addListener((observable, oldValue, newValue) -> {
            UUID imageId = imagesRepo.add(newValue);
            platform.setImageId(imageId);
        });
        url.addListener((observable, oldValue, newValue) -> platform.setUrl(newValue));
    }

    public Platform getPlatform() {
        return platform;
    }

    public SimpleObjectProperty<UUID> idProperty() {
        return id;
    }

    public UUID getId() {
        return idProperty().get();
    }

    public void setId(UUID id) {
        idProperty().set(id);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public String getUrl() {
        return urlProperty().get();
    }

    public void setUrl(String url) {
        urlProperty().set(url);
    }

}
