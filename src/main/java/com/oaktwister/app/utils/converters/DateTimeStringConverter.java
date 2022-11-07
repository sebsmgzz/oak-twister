package com.oaktwister.app.utils.converters;

import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import javafx.util.StringConverter;

import java.time.LocalDateTime;

public class DateTimeStringConverter extends StringConverter<LocalDateTime> {

    private final LocalDateTimeUtil util = new LocalDateTimeUtil(); // TODO: DI?
    private final String prefix;

    public DateTimeStringConverter() {
        this(null);
    }

    public DateTimeStringConverter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString(LocalDateTime dateTime) {
        String dateTimeString = util.toDefault(dateTime);
        if(prefix == null) {
            return dateTimeString;
        }
        return String.format("%s %s", prefix, dateTimeString);
    }

    @Override
    public LocalDateTime fromString(String string) {
        if(prefix == null) {
            return util.fromDefault(string);
        }
        String dateTimeString = string.substring(prefix.length());
        return util.fromDefault(dateTimeString);
    }

}
