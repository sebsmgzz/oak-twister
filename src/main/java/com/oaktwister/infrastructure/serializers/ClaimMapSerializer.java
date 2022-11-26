package com.oaktwister.infrastructure.serializers;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.ClaimMap;
import com.oaktwister.app.services.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClaimMapSerializer implements JsonArraySerializer<ClaimMap> {

    private final ClaimSerializer claimSerializer;
    private final Logger logger;

    public ClaimMapSerializer(ClaimSerializer claimSerializer, Logger logger) {
        this.claimSerializer = claimSerializer;
        this.logger = logger;
    }

    @Override
    public ClaimMap deserialize(JSONArray jsonArray) throws UnknownGrantTypeException {
        ClaimMap claimMap = new ClaimMap();
        for(int i = 0; i < jsonArray.length(); i++)
        {
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
