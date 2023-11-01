package edu.hw5.task5;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class CarNumberUtils {

    private static final Pattern CAR_NUMBER_PATTERN = Pattern.compile("^[A-ZА-Я\\d]{1}\\d{3}[A-ZА-Я]{2}\\d{3}$");

    private CarNumberUtils() {
    }

    public static boolean isCarNumberValid(String carNumber) {
        if (StringUtils.isBlank(carNumber)) {
            throw new IllegalArgumentException("carNumber should not be null or empty");
        }
        return CAR_NUMBER_PATTERN.matcher(carNumber).find();
    }
}
