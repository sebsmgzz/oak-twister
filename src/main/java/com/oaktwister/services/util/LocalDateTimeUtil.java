package com.oaktwister.services.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    private final static String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String UTC_ZONE_ID = "UTC";

    public LocalDateTime fromIso8601(String dateTimeString) {
        return LocalDateTime.parse(
                dateTimeString,
                DateTimeFormatter.ofPattern(ISO_8601_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

    public String toIso8601(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneOffset.UTC).toString();
    }

}
