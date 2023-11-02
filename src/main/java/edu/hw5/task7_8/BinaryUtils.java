package edu.hw5.task7_8;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class BinaryUtils {

    private static final String NULL_MESSAGE = "binary string should not be null";

    private BinaryUtils() {
    }

    // task 7

    // содержит не менее 3 символов, причем третий символ равен 0
    public static boolean regex1(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("[01]{2}0[01]+");
        return pattern.matcher(binaryString).find();
    }

    // начинается и заканчивается одним и тем же символом
    public static boolean regex2(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(0|1)([01]*)(\\1)$");
        return pattern.matcher(binaryString).find();
    }

    // длина не менее 1 и не более 3
    public static boolean regex3(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(binaryString).find();
    }

    // task 8

    // нет последовательных 1
    public static boolean regex4(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?!.*11)[01]*$");
        return pattern.matcher(binaryString).find();
    }

    // содержит не менее двух 0 и не более одной 1
    public static boolean regex5(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?=(.*0){2,})(?!(.*1){2,})[01]*$");
        return pattern.matcher(binaryString).find();
    }

    //любая строка, кроме 11 или 111
    public static boolean regex6(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?!11|111)[01]+$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean regex7(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(10)*1?$");
        return pattern.matcher(binaryString).find();
    }

}
