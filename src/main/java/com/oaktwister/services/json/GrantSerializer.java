package com.oaktwister.services.json;

import com.oaktwister.models.grants.*;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.util.extensions.LocalDateTimeUtil;
import org.json.JSONObject;

import java.util.Objects;

public class GrantSerializer implements JsonObjectSerializer<Grant<?>> {

    private final static String NAME_KEY = "name";
    private final static String TYPE_KEY = "type";
    private final static String VALUE_KEY = "value";

    private final Logger logger;
    private final LocalDateTimeUtil localDateTimeUtil;

    public GrantSerializer(LocalDateTimeUtil localDateTimeUtil, Logger logger) {
        this.localDateTimeUtil = localDateTimeUtil;
        this.logger = logger;
    }

    @Override
    public Grant<?> deserialize(JSONObject grantJson) throws UnknownGrantTypeException {
        String type = grantJson.getString(TYPE_KEY);
        if(Objects.equals(type, DateTimeGrant.class.getTypeName())) {
            return deserializeDateTimeGrant(grantJson);
        } else if (Objects.equals(type, FlagGrant.class.getTypeName())) {
            return deserializeFlagGrant(grantJson);
        } else if (Objects.equals(type, NumberGrant.class.getTypeName())) {
            return deserializeNumberGrant(grantJson);
        } else if (Objects.equals(type, SecretGrant.class.getTypeName())) {
            return deserializeSecretGrant(grantJson);
        } else if (Objects.equals(type, TextGrant.class.getTypeName())) {
            return deserializeTextGrant(grantJson);
        } else {
            throw new UnknownGrantTypeException(type);
        }
    }

    @Override
    public JSONObject serialize(Grant<?> entity) throws UnknownGrantTypeException {
        if(entity instanceof DateTimeGrant) {
            return serializeDateTimeGrant((DateTimeGrant) entity);
        } else if (entity instanceof FlagGrant) {
            return serializeFlagGrant((FlagGrant) entity);
        } else if (entity instanceof NumberGrant) {
            return serializeNumberGrant((NumberGrant) entity);
        } else if (entity instanceof SecretGrant) {
            return serializeSecretGrant((SecretGrant) entity);
        } else if (entity instanceof TextGrant) {
            return serializeTextGrant((TextGrant) entity);
        } else {
            throw new UnknownGrantTypeException(entity.getClass().getTypeName());
        }
    }

    private DateTimeGrant deserializeDateTimeGrant(JSONObject dateTimeGrantJson) {
        return new DateTimeGrant(
                dateTimeGrantJson.getString(NAME_KEY),
                localDateTimeUtil.fromIso8601(dateTimeGrantJson.getString(VALUE_KEY)));
    }

    private JSONObject serializeDateTimeGrant(DateTimeGrant dateTimeGrant) {
        JSONObject dateTimeGrantJson = new JSONObject();
        dateTimeGrantJson.put(NAME_KEY, dateTimeGrant.getName());
        dateTimeGrantJson.put(TYPE_KEY, DateTimeGrant.class.getTypeName());
        dateTimeGrantJson.put(VALUE_KEY, localDateTimeUtil.toIso8601(dateTimeGrant.getValue()));
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
