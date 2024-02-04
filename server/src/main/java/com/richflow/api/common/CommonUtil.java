package com.richflow.api.common;

import java.sql.Timestamp;
import java.util.Calendar;

public class CommonUtil {
    public static Timestamp getTimestamp(String type, int period) {
        int calendarField;
        switch (type.toUpperCase()) {
            case "YEAR":
                calendarField = Calendar.YEAR;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }

        Calendar cal = Calendar.getInstance();
        cal.add(calendarField, period);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getTimestamp() {
        Calendar cal = Calendar.getInstance();
        return new Timestamp(cal.getTimeInMillis());
    }
}