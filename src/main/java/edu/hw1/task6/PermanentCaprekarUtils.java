package edu.hw1.task6;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class PermanentCaprekarUtils {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int LOWER_BOUND = 1000;
    private final static int UPPER_BOUND = 9999;
    private final static int CARPERKAR_CONST = 6174;

    private PermanentCaprekarUtils() {
    }

    public static int countK(int number) {
        if (number <= LOWER_BOUND || number > UPPER_BOUND) {
            return -1;
        }
        char[] digits = String.valueOf(number).toCharArray();
        Arrays.sort(digits);
        String sortedNum = new String(digits);
        int increased = Integer.parseInt(sortedNum);
        int decreased = Integer.parseInt(new StringBuilder(sortedNum).reverse().toString());
        LOGGER.info(increased + " " + decreased);
        if (decreased - increased == CARPERKAR_CONST) {
            return 1;
        } else if (decreased - increased == 0) {
            return -1;
        }
        return 1 + countK(decreased - increased);
    }

}
