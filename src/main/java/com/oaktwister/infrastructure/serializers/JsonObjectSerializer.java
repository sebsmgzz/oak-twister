package com.oaktwister.infrastructure.serializers;

import com.oaktwister.domain.exceptions.UnknownMetaGrantException;
import org.json.JSONObject;

public interface JsonObjectSerializer<T> {

    T deserialize(JSONObject json) throws UnknownMetaGrantException;

    JSONObject serialize(T entity) throws UnknownMetaGrantException;

}
