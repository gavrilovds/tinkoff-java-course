package edu.hw1.task7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BitUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    private BitUtils() {
    }

    public static int rotateLeft(int number, int shift) {
        if (number < 0 || shift < 0) {
            return -1;
        }
        String binary = Integer.toBinaryString(number);
        StringBuilder newNumber = new StringBuilder(binary.replaceAll("1", "0"));
        LOGGER.info(binary);
        for (int i = 0; i < binary.length(); i++) {
            if (i < shift) {
                newNumber.replace(binary.length() - shift - i, binary.length() - shift - i + 1,
                    String.valueOf(binary.charAt(i))
                );
            } else {
                newNumber.replace(i - shift, i - shift + 1,
                    String.valueOf(binary.charAt(i))
                );
            }
            LOGGER.info(i + ": " + newNumber);
        }
        return Integer.parseInt(newNumber.toString(), 2);
    }

    public static int rotateRight(int number, int shift) {
        if (number < 0 || shift < 0) {
            return -1;
        }
        String binary = Integer.toBinaryString(number);
        StringBuilder newNumber = new StringBuilder(binary.replaceAll("1", "0"));
        LOGGER.info(binary);
        for (int i = 0; i < binary.length(); i++) {
            if (i + shift > binary.length() - 1) {
                int move = i + shift - binary.length();
                LOGGER.info("move : " + move);
                newNumber.replace(move, move + 1,
                    String.valueOf(binary.charAt(i))
                );
            } else {
                newNumber.replace(i + shift, i + shift + 1,
                    String.valueOf(binary.charAt(i))
                );
            }
            LOGGER.info(i + ": " + newNumber);
        }
        return Integer.parseInt(newNumber.toString(), 2);
    }

}
