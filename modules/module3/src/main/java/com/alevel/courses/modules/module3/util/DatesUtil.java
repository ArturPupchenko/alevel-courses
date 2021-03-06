package com.alevel.courses.modules.module3.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatesUtil {

    public static Timestamp formatStringISOtoTimestamp(String timestamp) {
        return Timestamp.valueOf(LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public static String formatTimestampToISO(Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String formatInstantToISO(Instant timestamp) {
        return formatTimestampToISO(Timestamp.from(timestamp));
    }

    public static LocalDateTime formatTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static String formatLocalDateTimeToStringISO(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
