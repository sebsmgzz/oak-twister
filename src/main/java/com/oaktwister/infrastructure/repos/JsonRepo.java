package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.seedwork.Entity;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.infrastructure.serializers.JsonObjectSerializer;
import com.oaktwister.app.services.logging.Logger;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.UUID;

public abstract class JsonRepo<T extends Entity> {

    public final static String FILE_EXTENSION = ".json";

    private final Session session;
    private final JsonObjectSerializer<T> jsonObjectSerializer;
    private final Logger logger;

    public JsonRepo(Session session, JsonObjectSerializer<T> jsonObjectSerializer, Logger logger) {
        this.session = session;
        this.jsonObjectSerializer = jsonObjectSerializer;
        this.logger = logger;
    }

    protected abstract String getRepoLocation();

    private Path getFullRepoLocation() throws InvalidSessionPropertyException {
        String drivePath = session.getDrive().getPath();
        String repoLocation = getRepoLocation();
        return Paths.get(drivePath, repoLocation);
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
        buffer.close();
        String json = builder.toString();
        return new JSONObject(json);
    }

    protected Path getEntityLocation(UUID id) throws InvalidSessionPropertyException {
        String fileName = id + FILE_EXTENSION;
        String fullRepoLocation = getFullRepoLocation().toString();
        return Paths.get(fullRepoLocation, fileName);
    }

    public ArrayList<T> findAll() throws IOException, InvalidSessionPropertyException {
        ArrayList<T> entities = new ArrayList<>();
        String fullRepoLocation = getFullRepoLocation().toString();
        File repoDirectory = new File(fullRepoLocation);
        File[] repoFiles = repoDirectory.listFiles();
        assert repoFiles != null;
        for (File repoFile : repoFiles) {
            String fileName = repoFile.getName();
            UUID id = UUID.fromString(fileName.replace(FILE_EXTENSION, ""));
            T entity = findById(id);
            entities.add(entity);
        }
        return entities;
    }

    public ArrayList<T> tryFindAll() {
        try {
            return findAll();
        } catch(IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            return new ArrayList<>();
        }
    }

    public T findById(UUID id) throws InvalidSessionPropertyException, IOException {
        String fileLocation = getEntityLocation(id).toString();
        logger.info("Searching %s", fileLocation);
        JSONObject json = rawJsonRead(fileLocation);
        return jsonObjectSerializer.deserialize(json);
    }

    public T tryFindById(UUID id) {
        try {
            return findById(id);
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            return null;
        }
    }

    public void add(T entity) throws InvalidSessionPropertyException, IOException {
        entity.setId(UUID.randomUUID());
        Path fileLocation = getEntityLocation(entity.getId());
        JSONObject json = jsonObjectSerializer.serialize(entity);
        logger.info("Adding %s", fileLocation.toString());
        Files.writeString(fileLocation, json.toString());
    }

    public boolean tryAdd(T entity) {
        try {
            add(entity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            return false;
        }
    }

    public void remove(T entity) throws InvalidSessionPropertyException, IOException {
        Path fileLocation = getEntityLocation(entity.getId());
        File file = new File(fileLocation.toString());
        logger.info("Deleting %s", fileLocation.toString());
        Files.delete(fileLocation);
    }

    public boolean tryRemove(T entity) {
        try {
            remove(entity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            return false;
        }
    }

    public void update(T entity) throws InvalidSessionPropertyException, IOException {
        Path fileLocation = getEntityLocation(entity.getId());
        JSONObject json = jsonObjectSerializer.serialize(entity);
        logger.info("Updating %s", fileLocation.toString());
        Files.writeString(fileLocation, json.toString(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    public boolean tryUpdate(T entity) {
        try {
            update(entity);
            return true;
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex, ex.getMessage());
            return false;
        }
    }

}
