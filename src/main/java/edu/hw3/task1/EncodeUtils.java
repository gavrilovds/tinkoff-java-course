package edu.hw3.task1;

import org.apache.commons.lang3.StringUtils;

public final class EncodeUtils {

    private static final int ASCII_CODE_OF_A = 65;
    private static final int ASCII_CODE_OF_Z = 90;
    private static final int ASCII_CODE_OF_LOWER_A = 97;
    private static final int ASCII_CODE_OF_LOWER_Z = 122;

    private EncodeUtils() {
    }

    public static String encodeWithAtbash(String message) {
        if (StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("Empty message");
        }
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentSymbol = message.charAt(i);
            if (!isAsciiLatinAlpha(currentSymbol)) {
                encodedMessage.append(currentSymbol);
                continue;
            }
            char mirrorLetter = getMirrorLetter(Character.toLowerCase(currentSymbol));
            if (Character.isUpperCase(currentSymbol)) {
                mirrorLetter = Character.toUpperCase(mirrorLetter);
            }
            encodedMessage.append(mirrorLetter);
        }
        return encodedMessage.toString();
    }

    private static char getMirrorLetter(char letter) {
        return (char) (ASCII_CODE_OF_LOWER_A - (letter - ASCII_CODE_OF_LOWER_Z));
    }

    private static boolean isAsciiLatinAlpha(char symbol) {
        return (symbol >= ASCII_CODE_OF_A && symbol <= ASCII_CODE_OF_Z)
            || (symbol >= ASCII_CODE_OF_LOWER_A && symbol <= ASCII_CODE_OF_LOWER_Z);
    }
}
