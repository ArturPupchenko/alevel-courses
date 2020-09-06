package com.alevel.courses.modules.module3.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatesUtil {

    public static String formatTimestampToISO(Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDateTime formatTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static String formatLocalDateTimeToStringISO(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
