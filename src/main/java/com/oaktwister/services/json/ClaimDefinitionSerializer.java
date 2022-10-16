package com.oaktwister.services.json;

import com.oaktwister.models.claims.*;
import org.json.JSONObject;

import java.util.Objects;

public class ClaimDefinitionSerializer implements Serializer<ClaimDefinition> {
    private final static String NAME_KEY = "name";
    private final static String TYPE_KEY = "type";
    private final static String IS_OPTIONAL_KEY = "isOptional";

    @Override
    public ClaimDefinition deserialize(JSONObject json) {
        return new ClaimDefinition(
            json.getString(NAME_KEY),
            getClaimType(json.getString(TYPE_KEY)),
            json.getBoolean(IS_OPTIONAL_KEY));
    }

    @Override
    public JSONObject serialize(ClaimDefinition claim) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, claim.getName());
        json.put(TYPE_KEY, claim.getType().getTypeName());
        json.put(IS_OPTIONAL_KEY, claim.getIsOptional());
        return json;
    }

    private Class<?> getClaimType(String claimTypeName) {
        if(Objects.equals(claimTypeName, DateTimeClaim.class.getTypeName())) {
            return DateTimeClaim.class;
        } else if (Objects.equals(claimTypeName, FlagClaim.class.getTypeName())) {
            return FlagClaim.class;
        } else if (Objects.equals(claimTypeName, NumberClaim.class.getTypeName())) {
            return NumberClaim.class;
        } else if (Objects.equals(claimTypeName, SecretClaim.class.getTypeName())) {
            return SecretClaim.class;
        } else {
            // TODO: Throw exception?
            return null;
        }
    }

}
