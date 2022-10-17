package com.oaktwister.services.json;

import com.oaktwister.models.claims.Claim;
import com.oaktwister.models.claims.ClaimMap;
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
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonClaim = jsonArray.getJSONObject(i);
            Claim<?> claim = claimSerializer.deserialize(jsonClaim);
            claimMap.add(claim);
        }
        return claimMap;
    }

    @Override
    public JSONArray serialize(ClaimMap entity) {
        JSONArray jsonArray = new JSONArray();
        for(Claim<?> claim : entity) {
            JSONObject jsonClaim = claimSerializer.serialize(claim);
            jsonArray.put(jsonClaim);
        }
        return jsonArray;
    }

}
