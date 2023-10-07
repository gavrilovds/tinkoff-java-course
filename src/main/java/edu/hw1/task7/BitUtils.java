package edu.hw1.task7;

public final class BitUtils {

    private BitUtils() {
    }

    public static int rotateLeft(int number, int shift) {
        if (number < 0 || shift < 0) {
            return -1;
        }
        int numberOfBits = Integer.toBinaryString(number).length();
        int newShift = shift % numberOfBits;
        return (number << newShift | number >> (numberOfBits - newShift)) & ((1 << numberOfBits) - 1);
    }

    public static int rotateRight(int number, int shift) {
        if (number < 0 || shift < 0) {
            return -1;
        }
        int numberOfBits = Integer.toBinaryString(number).length();
        int newShift = shift % numberOfBits;
        return (number >> newShift | number << (numberOfBits - newShift)) & ((1 << numberOfBits) - 1);
    }

}
