package com.oaktwister.utils.converters;

import javafx.util.StringConverter;

import java.util.UUID;

public class UUIDStringConverter extends StringConverter<UUID> {

    @Override
    public String toString(UUID uuid) {
        if(uuid == null) {
            return null;
        }
        return uuid.toString();
    }

    @Override
    public UUID fromString(String string) {
        if(string == null) {
            return null;
        }
        return UUID.fromString(string);
    }

}
