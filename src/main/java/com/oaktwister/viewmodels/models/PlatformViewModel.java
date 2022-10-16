package com.oaktwister.viewmodels.models;

import com.oaktwister.models.aggregators.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

public class PlatformViewModel {

    private final Platform platform;
    private final SimpleObjectProperty<UUID> id;
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Image> image;
    private final SimpleStringProperty url;

    public PlatformViewModel(Platform platform) {
        this.platform = platform;
        id = new SimpleObjectProperty<>(platform.getId());
        id.addListener((observable, oldValue, newValue) -> platform.setId(newValue));
        name = new SimpleStringProperty(platform.getName());
        name.addListener((observable, oldValue, newValue) -> platform.setName(newValue));
        image = new SimpleObjectProperty<>(decodeImage(platform.getImageUrl()));
        image.addListener((observable, oldValue, newValue) -> platform.setImageUrl(encodeImage(newValue)));
        url = new SimpleStringProperty(platform.getUrl());
        url.addListener((observable, oldValue, newValue) -> platform.setUrl(newValue));
    }

    public Platform getPlatform() {
        return platform;
    }

    public SimpleObjectProperty<UUID> idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    private Image decodeImage(String image) {
        InputStream base64image = new ByteArrayInputStream(image.getBytes());
        InputStream inputStream = Base64.getDecoder().wrap(base64image);
        return new Image(inputStream);
    }

    private String encodeImage(Image img) {
        int width = (int)img.getWidth();
        int height = (int)img.getHeight();
        byte[] buffer = new byte[width * height * 4];
        img.getPixelReader().getPixels(0, 0, width, height,
                PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);
        return Base64.getEncoder().encodeToString(buffer);
    }

}
