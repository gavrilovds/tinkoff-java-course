package edu.hw1.task5;

public final class PalindromeUtils {

    private static final int BASE = 10;

    private PalindromeUtils() {
    }

    public static boolean isPalindromeDescendant(int number) {
        int original = Math.abs(number);
        if (original < BASE) {
            return false;
        }
        int reversed = getReversedNumber(original);
        if (original == reversed) {
            return true;
        }
        int nextNumber = getNextNumber(reversed);
        return isPalindromeDescendant(nextNumber);
    }

    public static int getReversedNumber(int originNumber) {
        int reversed = 0;
        int tempNumber = originNumber;
        while (tempNumber > 0) {
            int residue = tempNumber % BASE;
            reversed = reversed * BASE + residue;
            tempNumber /= BASE;
        }
        return reversed;
    }

    public static int getNextNumber(int originNumber) {
        int tempNumber = originNumber;
        int nextNumber = 0;
        int degree = 1;
        while (tempNumber > 0) {
            int firstDigit = tempNumber % BASE;
            int secondDigit = (tempNumber / BASE) % BASE;
            if (firstDigit + secondDigit >= BASE && nextNumber != 0) {
                degree *= BASE;
            }
            nextNumber = nextNumber * degree + (firstDigit + secondDigit);
            tempNumber /= BASE * BASE;
            degree = BASE;
        }
        return nextNumber;
    }

}
