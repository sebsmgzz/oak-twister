package com.oaktwister.app.services.json;

import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.app.services.parsers.GrantTypeParser;
import org.json.JSONObject;

public class ClaimSerializer implements JsonObjectSerializer<Claim> {

    private final static String NAME_KEY = "name";
    private final static String GRANT_TYPE_KEY = "grantType";
    private final static String IS_OPTIONAL_KEY = "isOptional";

    private final GrantTypeParser grantTypeParser;
    private final Logger logger;

    public ClaimSerializer(GrantTypeParser grantTypeParser, Logger logger) {
        this.grantTypeParser = grantTypeParser;
        this.logger = logger;
    }

    @Override
    public Claim deserialize(JSONObject claimJson) throws UnknownGrantTypeException {
        String name = claimJson.getString(NAME_KEY);
        String grantNameType = claimJson.getString(GRANT_TYPE_KEY);
        Class<? extends Grant<?>> grantType = grantTypeParser.getGrantType(grantNameType);
        boolean isOptional = claimJson.getBoolean(IS_OPTIONAL_KEY);
        return new Claim(name, grantType, isOptional);
    }

    @Override
    public JSONObject serialize(Claim claim) {
        JSONObject claimJson = new JSONObject();
        claimJson.put(NAME_KEY, claim.getName());
        Class<? extends Grant<?>> grantType = claim.getGrantType();
        claimJson.put(GRANT_TYPE_KEY, grantTypeParser.getGrantTypeName(grantType));
        claimJson.put(IS_OPTIONAL_KEY, claim.getIsOptional());
        return claimJson;
    }

}
