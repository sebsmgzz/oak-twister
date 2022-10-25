package com.oaktwister.utils.extensions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    public final static String DEFAULT_PATTERN = "yyyy/MM/dd";
    public final static String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public final static String UTC_ZONE_ID = "UTC";

    public static LocalDateTime fromIso8601(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern(ISO_8601_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

    public static String toIso8601(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(ISO_8601_PATTERN).format(dateTime);
    }

    public static LocalDateTime fromDefault(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern(DEFAULT_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

    public static String toDefault(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneOffset.UTC).format(
                DateTimeFormatter.ofPattern(DEFAULT_PATTERN).withZone(ZoneId.of(UTC_ZONE_ID)));
    }

}
