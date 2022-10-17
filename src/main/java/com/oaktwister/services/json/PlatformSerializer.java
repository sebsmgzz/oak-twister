package com.oaktwister.services.json;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.models.claims.ClaimDefinition;
import com.oaktwister.models.claims.ClaimDefinitionMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PlatformSerializer implements JsonObjectSerializer<Platform> {

    public final static String ID_KEY = "id";
    public final static String NAME_KEY = "name";
    public final static String URL_KEY = "url";
    public final static String IMAGE_ID_KEY = "imageId";
    public final static String CREATED_AT_KEY = "createdAt";
    public final static String CLAIM_DEFINITIONS_KEY = "claimDefinitions";
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String DATE_TIME_ZONE = "UTC";

    private final ClaimDefinitionMapSerializer claimDefinitionMapSerializer;
    private final DateTimeFormatter formatter;

    public PlatformSerializer(ClaimDefinitionMapSerializer claimDefinitionMapSerializer) {
        this.claimDefinitionMapSerializer = claimDefinitionMapSerializer;
        formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).withZone(ZoneId.of(DATE_TIME_ZONE));
    }

    @Override
    public Platform deserialize(JSONObject json) {
        Platform platform = new Platform(
            UUID.fromString(json.getString(ID_KEY)),
            json.getString(NAME_KEY),
            UUID.fromString(json.getString(IMAGE_ID_KEY)),
            json.getString(URL_KEY),
            LocalDateTime.parse(json.getString(CREATED_AT_KEY), formatter));
        JSONArray jsonArray = json.getJSONArray(CLAIM_DEFINITIONS_KEY);
        ClaimDefinitionMap claimDefinitionMap = claimDefinitionMapSerializer.deserialize(jsonArray);
        platform.setClaimDefinitions(claimDefinitionMap);
        return platform;
    }

    @Override
    public JSONObject serialize(Platform platform) {
        JSONObject json = new JSONObject();
        json.put(ID_KEY, platform.getId().toString());
        json.put(NAME_KEY, platform.getName());
        json.put(URL_KEY, platform.getUrl());
        json.put(IMAGE_ID_KEY, platform.getImageId().toString());
        json.put(CLAIM_DEFINITIONS_KEY, claimDefinitionMapSerializer.serialize(platform.getClaimDefinitions()));
        return json;
    }

}
