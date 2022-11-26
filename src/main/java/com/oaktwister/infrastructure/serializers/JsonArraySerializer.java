package com.oaktwister.infrastructure.serializers;

import com.oaktwister.domain.exceptions.UnknownMetaGrantException;
import org.json.JSONArray;

public interface JsonArraySerializer<T> {

    T deserialize(JSONArray jsonArray) throws UnknownMetaGrantException;

    JSONArray serialize(T entity) throws UnknownMetaGrantException;

}
