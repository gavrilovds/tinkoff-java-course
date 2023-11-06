package edu.hw5.task7_8;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class BinaryUtils {

    private static final String NULL_MESSAGE = "binary string should not be null";

    private BinaryUtils() {
    }

    // task 7

    public static boolean doesContainsAtLeast3SymbolsAndNullIsThird(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("[01]{2}0[01]+");
        return pattern.matcher(binaryString).find();
    }

    public static boolean doesStartAndEndWithTheSameSymbol(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(0|1)([01]*)(\\1)$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean isLengthMoreThan1AndLessThan4(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(binaryString).find();
    }

    // task 8

    public static boolean doesNotContainConsecutiveOnes(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?!.*11)[01]*$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean doesContainAtLeastTwoZerosAndLessThanTwoOnes(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?=(.*0){2,})(?!(.*1){2,})[01]*$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean isNotTwoOrThreeConsecutiveOnes(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(?!11|111)[01]+$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean isLengthOdd(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(0|1)((00)|(01)|(10)|(11))*$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean isEveryOddSymbolOne(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^1(0$|1$|(01)|(11)$)*$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean isCountOfZerosMultipleOfThree(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(1*01*01*01*)+$");
        return pattern.matcher(binaryString).find();
    }

    public static boolean doesStartWithZeroAndHaveOddLengthOrStartWithOneAndHaveEvenLength(String binaryString) {
        if (StringUtils.isBlank(binaryString)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        final Pattern pattern = Pattern.compile("^(0((00)|(01)|(10)|(11))*|1([01])((00)|(01)|(10)|(11))*)$");
        return pattern.matcher(binaryString).find();
    }

}
