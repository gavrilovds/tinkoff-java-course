package edu.hw1.task2;

public final class DigitsUtils {
    private DigitsUtils() {
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int digitsCounter = 0;
        int tmp = Math.abs(number);
        final int step = 10;

        while (tmp > 0) {
            digitsCounter++;
            tmp /= step;
        }

        return digitsCounter;
    }
}
