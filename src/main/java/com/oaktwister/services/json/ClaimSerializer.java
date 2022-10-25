package com.oaktwister.services.json;

import com.oaktwister.models.grants.*;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.models.claims.*;
import com.oaktwister.services.logging.Logger;
import org.json.JSONObject;

import java.util.Objects;

public class ClaimSerializer implements JsonObjectSerializer<Claim> {

    private final static String NAME_KEY = "name";
    private final static String GRANT_TYPE_KEY = "grantType";
    private final static String IS_OPTIONAL_KEY = "isOptional";

    private final Logger logger;

    public ClaimSerializer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Claim deserialize(JSONObject claimJson) throws UnknownGrantTypeException {
        return new Claim(
            claimJson.getString(NAME_KEY),
            getGrantType(claimJson.getString(GRANT_TYPE_KEY)),
            claimJson.getBoolean(IS_OPTIONAL_KEY));
    }

    @Override
    public JSONObject serialize(Claim claim) {
        JSONObject claimJson = new JSONObject();
        claimJson.put(NAME_KEY, claim.getName());
        claimJson.put(GRANT_TYPE_KEY, claim.getGrantType().getTypeName());
        claimJson.put(IS_OPTIONAL_KEY, claim.getIsOptional());
        return claimJson;
    }

    private Class<? extends Grant<?>> getGrantType(String grantTypeName) throws UnknownGrantTypeException {
        if(Objects.equals(grantTypeName, DateTimeGrant.class.getTypeName())) {
            return DateTimeGrant.class;
        } else if (Objects.equals(grantTypeName, FlagGrant.class.getTypeName())) {
            return FlagGrant.class;
        } else if (Objects.equals(grantTypeName, NumberGrant.class.getTypeName())) {
            return NumberGrant.class;
        } else if (Objects.equals(grantTypeName, SecretGrant.class.getTypeName())) {
            return SecretGrant.class;
        } else if (Objects.equals(grantTypeName, TextGrant.class.getTypeName())) {
            return TextGrant.class;
        } else {
            throw new UnknownGrantTypeException(grantTypeName);
        }
    }

}
