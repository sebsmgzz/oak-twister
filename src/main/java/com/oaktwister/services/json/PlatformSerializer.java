package com.oaktwister.services.json;

import com.oaktwister.models.Platform;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.models.claims.ClaimMap;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.UUID;

public class PlatformSerializer implements JsonObjectSerializer<Platform> {

    public final static String ID_KEY = "id";
    public final static String NAME_KEY = "name";
    public final static String URL_KEY = "url";
    public final static String IMAGE_ID_KEY = "imageId";
    public final static String CREATED_AT_KEY = "createdAt";
    public final static String CLAIMS_KEY = "claims";

    private final ClaimMapSerializer claimMapSerializer;
    private final LocalDateTimeUtil localDateTimeUtil;

    public PlatformSerializer(ClaimMapSerializer claimMapSerializer, LocalDateTimeUtil localDateTimeUtil) {
        this.claimMapSerializer = claimMapSerializer;
        this.localDateTimeUtil = localDateTimeUtil;
    }

    @Override
    public Platform deserialize(JSONObject json) throws UnknownGrantTypeException {
        Platform platform = new Platform(
                UUID.fromString(json.getString(ID_KEY)),
                json.getString(NAME_KEY),
                UUID.fromString(json.getString(IMAGE_ID_KEY)),
                json.getString(URL_KEY),
                localDateTimeUtil.fromIso8601(json.getString(CREATED_AT_KEY)));
        JSONArray jsonArray = json.getJSONArray(CLAIMS_KEY);
        ClaimMap claimMap = claimMapSerializer.deserialize(jsonArray);
        platform.setClaims(claimMap);
        return platform;
    }

    @Override
    public JSONObject serialize(Platform platform) {
        JSONObject json = new JSONObject();
        json.put(ID_KEY, platform.getId().toString());
        json.put(NAME_KEY, platform.getName());
        json.put(URL_KEY, platform.getUrl());
        json.put(IMAGE_ID_KEY, platform.getImageId().toString());
        json.put(CREATED_AT_KEY, localDateTimeUtil.toIso8601(platform.getCreatedAt()));
        json.put(CLAIMS_KEY, claimMapSerializer.serialize(platform.getClaims()));
        return json;
    }

}
