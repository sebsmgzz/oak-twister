package com.oaktwister.infrastructure.serializers.claims;

import com.oaktwister.domain.exceptions.UnknownMetaGrantException;
import com.oaktwister.domain.models.claims.Claim;
import com.oaktwister.domain.models.claims.MetaGrant;
import com.oaktwister.infrastructure.serializers.JsonObjectSerializer;
import org.json.JSONObject;

public class ClaimSerializer implements JsonObjectSerializer<Claim> {

    private final static String NAME_KEY = "name";
    private final static String META_GRANT_NAME_KEY = "grantName";
    private final static String IS_OPTIONAL_KEY = "isOptional";

    @Override
    public Claim deserialize(JSONObject claimJson) {
        try {
            String name = claimJson.getString(NAME_KEY);
            MetaGrant metaGrant = MetaGrant.parse(claimJson.getString(META_GRANT_NAME_KEY));
            boolean isOptional = claimJson.getBoolean(IS_OPTIONAL_KEY);
            return new Claim(name, metaGrant, isOptional);
        } catch (UnknownMetaGrantException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    @Override
    public JSONObject serialize(Claim claim) {
        JSONObject claimJson = new JSONObject();
        claimJson.put(NAME_KEY, claim.getName());
        claimJson.put(META_GRANT_NAME_KEY, claim.getMetaGrant().getName());
        claimJson.put(IS_OPTIONAL_KEY, claim.getIsOptional());
        return claimJson;
    }

}