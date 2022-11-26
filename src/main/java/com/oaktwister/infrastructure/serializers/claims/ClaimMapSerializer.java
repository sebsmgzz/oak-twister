package com.oaktwister.infrastructure.serializers.claims;

import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.ClaimMap;
import com.oaktwister.infrastructure.serializers.JsonArraySerializer;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClaimMapSerializer implements JsonArraySerializer<ClaimMap> {

    private final ClaimSerializer claimSerializer;

    public ClaimMapSerializer(ClaimSerializer claimSerializer) {
        this.claimSerializer = claimSerializer;
    }

    @Override
    public ClaimMap deserialize(JSONArray jsonArray) {
        ClaimMap claimMap = new ClaimMap();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonClaim = jsonArray.getJSONObject(i);
            Claim claim = claimSerializer.deserialize(jsonClaim);
            claimMap.add(claim);
        }
        return claimMap;
    }

    @Override
    public JSONArray serialize(ClaimMap claimMap) {
        JSONArray claimMapJson = new JSONArray();
        for(Claim claim : claimMap) {
            JSONObject claimJson = claimSerializer.serialize(claim);
            claimMapJson.put(claimJson);
        }
        return claimMapJson;
    }

}
