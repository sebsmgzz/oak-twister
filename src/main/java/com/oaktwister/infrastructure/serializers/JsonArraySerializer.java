package com.oaktwister.infrastructure.serializers;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import org.json.JSONArray;

public interface JsonArraySerializer<T> {

    T deserialize(JSONArray jsonArray) throws UnknownGrantTypeException;

    JSONArray serialize(T entity) throws UnknownGrantTypeException;

}
