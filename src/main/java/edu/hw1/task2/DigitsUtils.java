package edu.hw1.task2;

public final class DigitsUtils {

    public static final int STEP = 10;

    private DigitsUtils() {
    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int digitsCounter = 0;
        int tmp = Math.abs(number);

        while (tmp > 0) {
            digitsCounter++;
            tmp /= STEP;
        }

        return digitsCounter;
    }
}
