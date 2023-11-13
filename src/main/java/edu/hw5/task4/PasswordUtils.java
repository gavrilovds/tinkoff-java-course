package edu.hw5.task4;

import org.apache.commons.lang3.StringUtils;

public final class PasswordUtils {

    private static final String REQUIRED_SYMBOLS_REGEX = ".*[~!@#\\$%^&*|].*";

    private PasswordUtils() {
    }

    public static boolean isPasswordValid(String password) {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("password should not be empty or null");
        }
        return password.matches(REQUIRED_SYMBOLS_REGEX);
    }
}
