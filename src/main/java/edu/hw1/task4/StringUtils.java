package edu.hw1.task4;

public final class StringUtils {

    private StringUtils() {
    }

    public static String fixString(String messedUpString) {
        if (messedUpString == null || messedUpString.isEmpty()) {
            return "";
        }
        StringBuilder fixedString = new StringBuilder();
        for (int i = 1; i < messedUpString.length(); i += 2) {
            fixedString.append(messedUpString.charAt(i));
            fixedString.append(messedUpString.charAt(i - 1));
        }
        if (messedUpString.length() % 2 != 0) {
            fixedString.append(messedUpString.charAt(messedUpString.length() - 1));
        }
        return fixedString.toString();
    }

}
