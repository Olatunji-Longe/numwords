package com.numwords.common;

/**
 * @author Olatunji O. Longe
 * @since 05 May, 2021, 7:37 p.m.
 */
public class Dictionary {

    public static final String ZERO = "zero";
    public static final String[] UNITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static final String[] TEENS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] DOUBLE_DIGIT_TENS = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public static String capitalize(String string) {
        return string == null ? null : string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String split(String string, String separator) {
        return string == null ? null : string.substring(string.lastIndexOf(separator) + 1);
    }
}
