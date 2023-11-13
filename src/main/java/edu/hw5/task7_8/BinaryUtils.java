package edu.hw5.task7_8;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class BinaryUtils {

    public static final Pattern CONTAINS_AT_LEAST_3_SYMBOLS_AND_ZERO_IS_THIRD = Pattern.compile("[01]{2}0[01]+");
    public static final Pattern STARTS_AND_ENDS_WITH_SAME_SYMBOL = Pattern.compile("^(0|1)([01]*)(\\1)$");
    public static final Pattern LENGTH_MORE_THAN_ONE_AND_LESS_THAN_FOUR = Pattern.compile("^[01]{1,3}$");
    public static final Pattern NO_CONSECUTIVE_ONES = Pattern.compile("^(?!.*11)[01]*$");
    public static final Pattern AT_LEAST_TWO_ZEROS_AND_LESS_THAN_TWO_ONES =
        Pattern.compile("^(?=(.*0){2,})(?!(.*1){2,})[01]*$");
    public static final Pattern NOT_TWO_OR_THREE_CONSECUTIVE_ONES = Pattern.compile("^(?!11|111)[01]+$");
    public static final Pattern ODD_LENGTH = Pattern.compile("^(0|1)((00)|(01)|(10)|(11))*$");
    public static final Pattern ALL_ODD_SYMBOLS_ONES = Pattern.compile("^1(0$|1$|(01)|(11)$)*$");
    public static final Pattern COUNT_OF_ZEROS_MULTIPLE_OF_THREE = Pattern.compile("^(1*01*01*01*)+$");
    public static final Pattern START_WITH_ZERO_AND_ODD_LENGTH_OR_START_WITH_ONE_AND_EVEN_LENGTH =
        Pattern.compile("^(0((00)|(01)|(10)|(11))*|1([01])((00)|(01)|(10)|(11))*)$");

    private BinaryUtils() {
    }

    public static boolean doesSatisfyPattern(String binaryString, Pattern pattern) {
        if (StringUtils.isBlank(binaryString)) {
            return false;
        }
        return pattern.matcher(binaryString).find();
    }
}
