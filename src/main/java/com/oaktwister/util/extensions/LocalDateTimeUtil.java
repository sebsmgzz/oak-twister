package com.oaktwister.util.extensions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    private final static String DEFAULT_PATTERN = "yyyy/MM/dd";
    private final static String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String UTC_ZONE_ID = "UTC";

    public LocalDateTime fromIso8601(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern(ISO_8601_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

    public String toIso8601(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(ISO_8601_PATTERN).format(dateTime);
    }

    public LocalDateTime fromDefault(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern(DEFAULT_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

    public String toDefault(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneOffset.UTC).format(
                DateTimeFormatter.ofPattern(DEFAULT_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

}
