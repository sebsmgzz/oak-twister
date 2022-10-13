package com.oaktwister.services;

import com.oaktwister.models.Platform;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class JsonParser {

    public JSONObject deserialize(String jsonLocation) throws IOException {
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

    public Platform deserializePlatform(String jsonLocation) throws IOException {
        JSONObject json = deserialize(jsonLocation);
        return new Platform(
                UUID.fromString(json.getString("id")),
                json.getString("name"),
                json.getString("image"),
                json.getString("url"));
    }

    public String serializePlatform(Platform platform) {
        JSONObject json = new JSONObject();
        json.put("id", platform.getId().toString());
        json.put("name", platform.getName());
        json.put("url", platform.getUrl());
        return json.toString();
    }

}
