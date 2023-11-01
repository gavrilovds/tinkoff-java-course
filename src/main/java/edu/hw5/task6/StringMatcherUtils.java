package edu.hw5.task6;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class StringMatcherUtils {

    private StringMatcherUtils() {
    }

    // Не уверен, что правильно понял задание. Возможно требовалось как-то по-другому через регулярки делать
    public static boolean isStringSubstringOf(String substring, String fullString) {
        if (StringUtils.isBlank(substring) || StringUtils.isBlank(fullString)) {
            throw new IllegalArgumentException("argument(-s) should not be null or empty");
        }
        Pattern substringPattern = Pattern.compile(".*(" + Pattern.quote(substring) + ").*");
        return substringPattern.matcher(fullString).find();
    }
}
