package com.oaktwister.services.json;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.models.claims.Claim;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class IdentitySerializer implements Serializer<Identity> {
    private final static String ID_KEY = "id";
    private final static String CREATED_AT_KEY = "createdAt";
    private final static String CLAIMS_KEY = "claims";
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String DATE_TIME_ZONE = "UTC";

    private final ClaimSerializer claimSerializer = new ClaimSerializer();
    private final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(DATE_TIME_FORMAT)
            .withZone(ZoneId.of(DATE_TIME_ZONE));

    @Override
    public Identity deserialize(JSONObject json) {
        Identity identity = new Identity(
            UUID.fromString(json.getString(ID_KEY)),
            LocalDateTime.parse(json.getString(CREATED_AT_KEY), formatter));
        for (Object obj : json.getJSONArray(CLAIMS_KEY)) {
            JSONObject jsonClaim = new JSONObject(obj);
            Claim<?> claim = claimSerializer.deserialize(jsonClaim);
            identity.claims().add(claim);
        }
        return identity;
    }

    @Override
    public JSONObject serialize(Identity entity) {
        JSONObject json = new JSONObject();
        json.put(ID_KEY, entity.getId());
        json.put(CREATED_AT_KEY, entity.getCreatedAt());
        JSONArray jArray = new JSONArray();
        for(Claim<?> claim : entity.claims()) {
            jArray.put(claimSerializer.serialize(claim));
        }
        json.put(CLAIMS_KEY, jArray);
        return json;
    }

}
