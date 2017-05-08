package gf.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Helper functions for handling dates.
 *
 * @author Marco Jakob
 */
public class DateUtil {

    /**
     * The date pattern that is used for conversion. Change as you wish.
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * The date formatter.
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     * <p>
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            if (dateString != null && dateString != "null")
                return DATE_FORMATTER.parse(dateString, LocalDate::from);
            else
                return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDate parse(Date date) {
        try {
            if (date != null) {
                return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                return DATE_FORMATTER.parse(dateString, LocalDate::from);
            } else {
            }
                return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static Date parseToDate(LocalDate localDate) {
        try {
            if (localDate != null) {

                return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                return DATE_FORMATTER.parse(dateString, LocalDate::from);
            } else {
            }
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static long parseToLong(LocalDate localDate) {
        try {
            if (localDate != null) {

                return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
//                return DATE_FORMATTER.parse(dateString, LocalDate::from);
            } else {
            }
            return -1;
        } catch (DateTimeParseException e) {
            return -1;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}