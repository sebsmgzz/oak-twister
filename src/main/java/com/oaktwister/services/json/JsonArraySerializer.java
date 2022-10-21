package com.oaktwister.services.json;

import com.oaktwister.exceptions.UnknownGrantTypeException;
import org.json.JSONArray;

public interface JsonArraySerializer<T> {

    T deserialize(JSONArray jsonArray) throws UnknownGrantTypeException;

    JSONArray serialize(T entity) throws UnknownGrantTypeException;

}
