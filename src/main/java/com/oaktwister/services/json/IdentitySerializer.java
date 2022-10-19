package com.oaktwister.services.json;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.models.exceptions.UnknownGrantTypeException;
import com.oaktwister.models.props.Grant;
import com.oaktwister.models.props.GrantMap;
import com.oaktwister.services.util.LocalDateTimeUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class IdentitySerializer implements JsonObjectSerializer<Identity> {

    private final static String ID_KEY = "id";
    private final static String CREATED_AT_KEY = "createdAt";
    private final static String GRANT_MAP_KEY = "grants";

    private final GrantMapSerializer grantMapSerializer;
    private final LocalDateTimeUtil localDateTimeUtil;

    public IdentitySerializer(GrantMapSerializer grantMapSerializer, LocalDateTimeUtil localDateTimeUtil) {
        this.grantMapSerializer = grantMapSerializer;
        this.localDateTimeUtil = localDateTimeUtil;
    }

    @Override
    public Identity deserialize(JSONObject identityJson) throws UnknownGrantTypeException {
        Identity identity = new Identity(
            UUID.fromString(identityJson.getString(ID_KEY)),
            localDateTimeUtil.fromIso8601(identityJson.getString(CREATED_AT_KEY)));
        identity.setGrants(grantMapSerializer.deserialize(identityJson.getJSONArray(GRANT_MAP_KEY)));
        return identity;
    }

    @Override
    public JSONObject serialize(Identity identity) {
        JSONObject identityJson = new JSONObject();
        identityJson.put(ID_KEY, identity.getId());
        identityJson.put(CREATED_AT_KEY, identity.getCreatedAt());
        identityJson.put(GRANT_MAP_KEY, grantMapSerializer.serialize(identity.getGrants()));
        return identityJson;
    }

}
