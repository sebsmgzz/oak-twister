package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.GrantMap;
import com.oaktwister.infrastructure.serializers.JsonArraySerializer;
import org.json.JSONArray;
import org.json.JSONObject;

public class GrantMapSerializer implements JsonArraySerializer<GrantMap> {

    private final AnyGrantSerializer grantSerializer;

    public GrantMapSerializer(AnyGrantSerializer grantSerializer) {
        this.grantSerializer = grantSerializer;
    }

    @Override
    public GrantMap deserialize(JSONArray grantMapJson) {
        GrantMap grantMap = new GrantMap();
        for(int i = 0; i < grantMapJson.length(); i++) {
            JSONObject grantJson = grantMapJson.getJSONObject(i);
            Grant<?> grant = grantSerializer.deserialize(grantJson);
            grantMap.add(grant);
        }
        return grantMap;
    }

    @Override
    public JSONArray serialize(GrantMap grantMap) {
        JSONArray grantMapJson = new JSONArray();
        for(Grant<?> grant : grantMap) {
            JSONObject grantJson = grantSerializer.serialize(grant);
            grantMapJson.put(grantJson);
        }
        return grantMapJson;
    }

}
