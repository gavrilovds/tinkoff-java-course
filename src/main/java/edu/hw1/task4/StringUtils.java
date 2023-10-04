package edu.hw1.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StringUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    private StringUtils() {
    }

    public static String fixString(String toFix) {
        if (toFix == null || toFix.isEmpty()) {
            return "";
        }
        StringBuilder fixedString = new StringBuilder();
        for (int i = 1; i < toFix.length(); i += 2) {
            fixedString.append(toFix.charAt(i));
            fixedString.append(toFix.charAt(i - 1));
        }
        if (toFix.length() % 2 != 0) {
            fixedString.append(toFix.charAt(toFix.length() - 1));
        }
        return fixedString.toString();
    }

}
