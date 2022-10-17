package com.oaktwister.services.json;

import com.oaktwister.models.claims.ClaimDefinition;
import com.oaktwister.models.claims.ClaimDefinitionMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClaimDefinitionMapSerializer implements JsonArraySerializer<ClaimDefinitionMap> {

    private final ClaimDefinitionSerializer claimDefinitionSerializer;

    public ClaimDefinitionMapSerializer(ClaimDefinitionSerializer claimDefinitionSerializer) {
        this.claimDefinitionSerializer = claimDefinitionSerializer;
    }

    @Override
    public ClaimDefinitionMap deserialize(JSONArray jsonArray) {
        ClaimDefinitionMap claimDefinitionMap = new ClaimDefinitionMap();
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonClaimDefinition = jsonArray.getJSONObject(i);
            ClaimDefinition claimDefinition = claimDefinitionSerializer.deserialize(jsonClaimDefinition);
            claimDefinitionMap.add(claimDefinition);
        }
        return claimDefinitionMap;
    }

    @Override
    public JSONArray serialize(ClaimDefinitionMap claimDefinitionMap) {
        JSONArray jsonArray = new JSONArray();
        for(ClaimDefinition claimDefinition : claimDefinitionMap) {
            JSONObject jsonClaimDefinition = claimDefinitionSerializer.serialize(claimDefinition);
            jsonArray.put(jsonClaimDefinition);
        }
        return jsonArray;
    }

}
