package com.oaktwister.services;

import com.oaktwister.models.Drive;
import com.oaktwister.models.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlatformRepository {

    public static final String LOCATION = "platforms";
    private final Context context;
    private final JsonParser jsonParser;

    public PlatformRepository(Context context, JsonParser jsonParser) {
        this.context = context;
        this.jsonParser = jsonParser;
    }

    private Path getRepositoryLocation() {
        return Paths.get(context.getDrive().getPath(), LOCATION);
    }

    private  Path getPlatformLocation(UUID id) {
        String repoLocation = getRepositoryLocation().toString();
        String fileName = id + ".json";
        return Paths.get(repoLocation, fileName);
    }

    private Path getPlatformLocation(Platform platform) {
        return getPlatformLocation(platform.getId());
    }

    public List<Platform> findAllPlatforms() {
        ArrayList<Platform> platforms = new ArrayList<>();
        try {
            File repoDirectory = new File(getRepositoryLocation().toString());
            File[] repoFiles = repoDirectory.listFiles();
            assert repoFiles != null;
            for (File repoFile : repoFiles) {
                String fileName = repoFile.getName();
                UUID id = UUID.fromString(fileName.replace(".json", ""));
                Platform platform = findPlatformById(id);
                platforms.add(platform);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return platforms;
    }

    public Platform findPlatformById(UUID id) {
        try {
            String fileLocation = getPlatformLocation(id).toString();
            return jsonParser.deserializePlatform(fileLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean addPlatform(Platform platform) {
        try {
            platform.setId(UUID.randomUUID());
            Path fileLocation = getPlatformLocation(platform);
            String json = jsonParser.serializePlatform(platform);
            Files.writeString(fileLocation, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removePlatform(Platform platform) throws IOException {
        Path fileLocation = getPlatformLocation(platform);
        Files.delete(fileLocation);
    }

}
