package vsu.julia.holidaycalendarapp.util;

import java.util.regex.Pattern;

public class Utils {
    public static Boolean checkYear(String date) {
        return Pattern.compile("^\\d{4}$").matcher(date).find();
    }

    public static Boolean checkDate(String date) {
        return (Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$").matcher(date).find());
    }
}
