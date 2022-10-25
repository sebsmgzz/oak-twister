package com.oaktwister.services.json;

import com.oaktwister.models.Identity;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import org.json.JSONObject;

import java.util.UUID;

public class IdentitySerializer implements JsonObjectSerializer<Identity> {

    private final static String ID_KEY = "id";
    private final static String CREATED_AT_KEY = "createdAt";
    private final static String GRANT_MAP_KEY = "grants";

    private final GrantMapSerializer grantMapSerializer;

    public IdentitySerializer(GrantMapSerializer grantMapSerializer) {
        this.grantMapSerializer = grantMapSerializer;
    }

    @Override
    public Identity deserialize(JSONObject identityJson) throws UnknownGrantTypeException {
        Identity identity = new Identity(
            UUID.fromString(identityJson.getString(ID_KEY)),
            LocalDateTimeUtil.fromIso8601(identityJson.getString(CREATED_AT_KEY)));
        identity.setGrants(grantMapSerializer.deserialize(identityJson.getJSONArray(GRANT_MAP_KEY)));
        return identity;
    }

    @Override
    public JSONObject serialize(Identity identity) {
        JSONObject identityJson = new JSONObject();
        identityJson.put(ID_KEY, identity.getId());
        identityJson.put(CREATED_AT_KEY, identity.getCreatedAt());
        identityJson.put(GRANT_MAP_KEY, grantMapSerializer.serialize(identity.getGrantMap()));
        return identityJson;
    }

}
