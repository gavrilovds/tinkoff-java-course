package edu.hw3.task1;

public final class EncodeUtils {

    private static final int ASCII_CODE_OF_A = 97;
    private static final int ASCII_CODE_OF_Z = 122;

    private EncodeUtils() {
    }

    public static String encodeWithAtbash(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Empty message");
        }
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentSymbol = message.charAt(i);
            if (!Character.isLetter(currentSymbol)) {
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
        return (char) (ASCII_CODE_OF_Z - (letter - ASCII_CODE_OF_A));
    }
}
