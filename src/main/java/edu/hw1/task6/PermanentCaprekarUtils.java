package edu.hw1.task6;

import java.util.Arrays;

public final class PermanentCaprekarUtils {

    private static final int LOWER_BOUND = 1000;
    private static final int UPPER_BOUND = 9999;
    private static final int CARPERKAR_CONST = 6174;
    public static final int BASE = 10;
    public static final int MAX_SIZE = 4;

    private PermanentCaprekarUtils() {
    }

    public static int countK(int number) {
        if (number <= LOWER_BOUND || number > UPPER_BOUND) {
            return -1;
        }

        int[] digits = getDigits(number);
        Arrays.sort(digits);
        int lowerNumber = getLowerNumber(digits);
        int largerNumber = getLargerNumber(digits);
        if (largerNumber - lowerNumber == CARPERKAR_CONST) {
            return 1;
        } else if (largerNumber - lowerNumber == 0) {
            return -1;
        }
        return 1 + countK(largerNumber - lowerNumber);
    }

    public static int[] getDigits(int number) {
        int tempNumber = number;
        int[] digits = new int[MAX_SIZE];
        int i = 0;
        while (tempNumber > 0) {
            digits[i] = tempNumber % BASE;
            tempNumber /= BASE;
            i++;
        }
        return digits;
    }

    public static int getLowerNumber(int[] digits) {
        int number = 0;
        for (int i = 0; i < digits.length; i++) {
            number += (int) Math.pow(BASE, digits.length - 1 - i) * digits[i];
        }
        return number;
    }

    public static int getLargerNumber(int[] digits) {
        int number = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            number += (int) Math.pow(BASE, i) * digits[i];
        }
        return number;
    }

}
