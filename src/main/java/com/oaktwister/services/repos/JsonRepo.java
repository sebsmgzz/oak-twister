package com.oaktwister.services.repos;

import com.oaktwister.models.seedwork.Entity;
import com.oaktwister.services.Context;
import com.oaktwister.services.json.JsonObjectSerializer;
import com.oaktwister.services.logging.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public abstract class JsonRepo<T extends Entity> {

    public final static String FILE_EXTENSION = ".json";

    private final Context context;
    private final JsonObjectSerializer<T> jsonObjectSerializer;
    private final Logger logger;

    public JsonRepo(Context context, JsonObjectSerializer<T> jsonObjectSerializer, Logger logger) {
        this.context = context;
        this.jsonObjectSerializer = jsonObjectSerializer;
        this.logger = logger;
    }

    protected abstract String getRepoLocation();

    private Path getFullRepoLocation() {
        return Paths.get(context.getDrive().getPath(), getRepoLocation());
    }

    private JSONObject rawJsonRead(String jsonLocation) throws IOException {
        FileReader reader = new FileReader(jsonLocation);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        while(true) {
            String line = buffer.readLine();
            if(line == null) {
                break;
            }
            builder.append(line);
        }
        String json = builder.toString();
        return new JSONObject(json);
    }

    public Path getEntityLocation(UUID id) {
        String fileName = id + FILE_EXTENSION;
        return Paths.get(getFullRepoLocation().toString(), fileName);
    }

    public ArrayList<T> findAll() {
        ArrayList<T> entities = new ArrayList<>();
        try {
            File repoDirectory = new File(getFullRepoLocation().toString());
            File[] repoFiles = repoDirectory.listFiles();
            assert repoFiles != null;
            for (File repoFile : repoFiles) {
                String fileName = repoFile.getName();
                UUID id = UUID.fromString(fileName.replace(FILE_EXTENSION, ""));
                T entity = findById(id);
                entities.add(entity);
            }
        } catch (Exception ex) {
            logger.error(ex, ex.getMessage());
        }
        return entities;
    }

    public T findById(UUID id) {
        logger.debug(String.format("Searching entity %s", id));
        try {
            String fileLocation = getEntityLocation(id).toString();
            JSONObject json = rawJsonRead(fileLocation);
            return jsonObjectSerializer.deserialize(json);
        } catch (Exception ex) {
            logger.error(ex, ex.getMessage());
            return null;
        }
    }

    public boolean add(T entity) {
        try {
            entity.setId(UUID.randomUUID());
            Path fileLocation = getEntityLocation(entity.getId());
            JSONObject json = jsonObjectSerializer.serialize(entity);
            Files.writeString(fileLocation, json.toString());
            return true;
        } catch (IOException ex) {
            logger.error(ex, ex.getMessage());
            return false;
        }
    }

    public boolean remove(T entity) {
        try {
            Path fileLocation = getEntityLocation(entity.getId());
            Files.delete(fileLocation);
            return true;
        } catch (IOException ex) {
            logger.error(ex, ex.getMessage());
            return false;
        }
    }

}
