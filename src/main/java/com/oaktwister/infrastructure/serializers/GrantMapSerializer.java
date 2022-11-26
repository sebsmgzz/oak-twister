package com.oaktwister.infrastructure.serializers;

import com.oaktwister.domain.exceptions.UnknownMetaGrantException;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.app.services.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class GrantMapSerializer implements JsonArraySerializer<GrantMap> {

    private final GrantSerializer grantSerializer;
    private final Logger logger;

    public GrantMapSerializer(GrantSerializer grantSerializer, Logger logger) {
        this.grantSerializer = grantSerializer;
        this.logger = logger;
    }

    @Override
    public GrantMap deserialize(JSONArray grantMapJson) throws UnknownMetaGrantException {
        GrantMap grantMap = new GrantMap();
        for(int i = 0; i < grantMapJson.length(); i++) {
            JSONObject grantJson = grantMapJson.getJSONObject(i);
            Grant<?> grant = grantSerializer.deserialize(grantJson);
            grantMap.add(grant);
        }
        return grantMap;
    }

    @Override
    public JSONArray serialize(GrantMap grantMap) throws UnknownMetaGrantException {
        JSONArray grantMapJson = new JSONArray();
        for(Grant<?> grant : grantMap) {
            JSONObject grantJson = grantSerializer.serialize(grant);
            grantMapJson.put(grantJson);
        }
        return grantMapJson;
    }

}
