package com.gmail.maxsvynarchuk.persistence.util.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    public static String formatDate(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
