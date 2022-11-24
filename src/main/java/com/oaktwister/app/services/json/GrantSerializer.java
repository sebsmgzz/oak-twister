package com.oaktwister.app.services.json;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.claims.MetaGrant;
import com.oaktwister.domain.models.grants.DateTimeGrant;
import com.oaktwister.domain.models.grants.FlagGrant;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.NumberGrant;
import com.oaktwister.domain.models.grants.SecretGrant;
import com.oaktwister.domain.models.grants.TextGrant;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.app.services.parsers.GrantTypeParser;
import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import org.json.JSONObject;

public class GrantSerializer implements JsonObjectSerializer<Grant<?>> {

    private final static String NAME_KEY = "name";
    private final static String TYPE_KEY = "type";
    private final static String VALUE_KEY = "value";

    private final Logger logger;

    public GrantSerializer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Grant<?> deserialize(JSONObject grantJson) throws UnknownGrantTypeException {
        String grantTypeName = grantJson.getString(TYPE_KEY);
        MetaGrant metaGrant = MetaGrant.tryParse(grantTypeName);
        if(metaGrant == MetaGrant.DATE_TIME_META) {
            return deserializeDateTimeGrant(grantJson);
        } else if (metaGrant == MetaGrant.FLAG_GRANT_META) {
            return deserializeFlagGrant(grantJson);
        } else if (metaGrant == MetaGrant.NUMBER_GRANT_META) {
            return deserializeNumberGrant(grantJson);
        } else if (metaGrant == MetaGrant.SECRET_GRANT_META) {
            return deserializeSecretGrant(grantJson);
        } else if (metaGrant == MetaGrant.TEXT_GRANT_META) {
            return deserializeTextGrant(grantJson);
        } else {
            throw new UnknownGrantTypeException(grantTypeName);
        }
    }

    @Override
    public JSONObject serialize(Grant<?> grant) throws UnknownGrantTypeException {
        if(grant instanceof DateTimeGrant) {
            return serializeDateTimeGrant((DateTimeGrant) grant);
        } else if (grant instanceof FlagGrant) {
            return serializeFlagGrant((FlagGrant) grant);
        } else if (grant instanceof NumberGrant) {
            return serializeNumberGrant((NumberGrant) grant);
        } else if (grant instanceof SecretGrant) {
            return serializeSecretGrant((SecretGrant) grant);
        } else if (grant instanceof TextGrant) {
            return serializeTextGrant((TextGrant) grant);
        } else {
            throw new UnknownGrantTypeException(grant.getName());
        }
    }

    private DateTimeGrant deserializeDateTimeGrant(JSONObject dateTimeGrantJson) {
        return new DateTimeGrant(
                dateTimeGrantJson.getString(NAME_KEY),
                LocalDateTimeUtil.fromIso8601(dateTimeGrantJson.getString(VALUE_KEY)));
    }

    private JSONObject serializeDateTimeGrant(DateTimeGrant dateTimeGrant) {
        JSONObject dateTimeGrantJson = new JSONObject();
        dateTimeGrantJson.put(NAME_KEY, dateTimeGrant.getName());
        dateTimeGrantJson.put(TYPE_KEY, DateTimeGrant.class.getTypeName());
        dateTimeGrantJson.put(VALUE_KEY, LocalDateTimeUtil.toIso8601(dateTimeGrant.getValue()));
        return dateTimeGrantJson;
    }

    private FlagGrant deserializeFlagGrant(JSONObject flagGrantJson) {
        return new FlagGrant(
                flagGrantJson.getString(NAME_KEY),
                flagGrantJson.getBoolean(VALUE_KEY));
    }

    private JSONObject serializeFlagGrant(FlagGrant flagGrant) {
        JSONObject flagGrantJson = new JSONObject();
        flagGrantJson.put(NAME_KEY, flagGrant.getName());
        flagGrantJson.put(TYPE_KEY, FlagGrant.class.getTypeName());
        flagGrantJson.put(VALUE_KEY, flagGrant.getValue());
        return flagGrantJson;
    }

    private NumberGrant deserializeNumberGrant(JSONObject numberGrantJson) {
        return new NumberGrant(
                numberGrantJson.getString(NAME_KEY),
                numberGrantJson.getNumber(VALUE_KEY));
    }

    private JSONObject serializeNumberGrant(NumberGrant numberGrant) {
        JSONObject numberGrantJson = new JSONObject();
        numberGrantJson.put(NAME_KEY, numberGrant.getName());
        numberGrantJson.put(TYPE_KEY, NumberGrant.class.getTypeName());
        numberGrantJson.put(VALUE_KEY, numberGrant.getValue());
        return numberGrantJson;
    }

    private SecretGrant deserializeSecretGrant(JSONObject secretGrantJson) {
        final String SECRET_VALUE_KEY = "value";
        final String SECRET_HINT_KEY = "hint";
        JSONObject valueJson = secretGrantJson.getJSONObject(VALUE_KEY);
        return new SecretGrant(
                secretGrantJson.getString(NAME_KEY),
                valueJson.getString(SECRET_VALUE_KEY),
                valueJson.getString(SECRET_HINT_KEY));
    }

    private JSONObject serializeSecretGrant(SecretGrant secretGrant) {
        final String SECRET_VALUE_KEY = "value";
        final String SECRET_HINT_KEY = "hint";
        JSONObject valueJson = new JSONObject();
        valueJson.put(SECRET_VALUE_KEY, secretGrant.getValue());
        valueJson.put(SECRET_HINT_KEY, secretGrant.getHint());
        JSONObject secretGrantJson = new JSONObject();
        secretGrantJson.put(NAME_KEY, secretGrant.getName());
        secretGrantJson.put(TYPE_KEY, SecretGrant.class.getTypeName());
        secretGrantJson.put(VALUE_KEY, valueJson);
        return secretGrantJson;
    }

    private TextGrant deserializeTextGrant(JSONObject textGrantJson) {
        return new TextGrant(
                textGrantJson.getString(NAME_KEY),
                textGrantJson.getString(VALUE_KEY));
    }

    private JSONObject serializeTextGrant(TextGrant textGrant) {
        JSONObject textGrantJson = new JSONObject();
        textGrantJson.put(NAME_KEY, textGrant.getName());
        textGrantJson.put(TYPE_KEY, TextGrant.class.getTypeName());
        textGrantJson.put(VALUE_KEY, textGrant.getValue());
        return textGrantJson;
    }

}
