package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.claims.MetaGrant;
import com.oaktwister.domain.models.grants.*;
import org.json.JSONObject;

public final class AnyGrantSerializer extends GrantSerializer<Grant<?>> {

    private final DateTimeGrantSerializer dateTimeGrantSerializer;
    private final FlagGrantSerializer flagGrantSerializer;
    private final NumberGrantSerializer numberGrantSerializer;
    private final SecretGrantSerializer secretGrantSerializer;
    private final TextGrantSerializer textGrantSerializer;

    public AnyGrantSerializer() {
        // TODO: Everywhere else, all these come from parameters. Should it be the same here?
        dateTimeGrantSerializer = new DateTimeGrantSerializer();
        flagGrantSerializer = new FlagGrantSerializer();
        numberGrantSerializer = new NumberGrantSerializer();
        secretGrantSerializer = new SecretGrantSerializer();
        textGrantSerializer = new TextGrantSerializer();
    }

    @Override
    public Grant<?> deserialize(JSONObject json) {
        String grantType = json.getString(TYPE_KEY);
        MetaGrant metaGrant = MetaGrant.tryParse(grantType);
        if(metaGrant == MetaGrant.DATE_TIME_META) {
            return dateTimeGrantSerializer.deserialize(json);
        } else if (metaGrant == MetaGrant.FLAG_GRANT_META) {
            return flagGrantSerializer.deserialize(json);
        } else if (metaGrant == MetaGrant.NUMBER_GRANT_META) {
            return numberGrantSerializer.deserialize(json);
        } else if (metaGrant == MetaGrant.SECRET_GRANT_META) {
            return secretGrantSerializer.deserialize(json);
        } else if (metaGrant == MetaGrant.TEXT_GRANT_META) {
            return textGrantSerializer.deserialize(json);
        } else {
            String message = getUnknownGrantMessage(grantType);
            throw new UnsupportedOperationException(message);
        }
    }

    @Override
    public JSONObject serialize(Grant<?> grant) {
        if(grant instanceof DateTimeGrant dateTimeGrant) {
            return dateTimeGrantSerializer.serialize(dateTimeGrant);
        } else if (grant instanceof FlagGrant flagGrant) {
            return flagGrantSerializer.serialize(flagGrant);
        } else if (grant instanceof NumberGrant numberGrant) {
            return numberGrantSerializer.serialize(numberGrant);
        } else if (grant instanceof SecretGrant secretGrant) {
            return secretGrantSerializer.serialize(secretGrant);
        } else if (grant instanceof TextGrant textGrant) {
            return textGrantSerializer.serialize(textGrant);
        } else {
            String message = getUnknownGrantMessage(grant.getName());
            throw new UnsupportedOperationException(message);
        }
    }

    private String getUnknownGrantMessage(String grantTypeName) {
        return String.format("Grant type '%s' is not recognized", grantTypeName);
    }

}
