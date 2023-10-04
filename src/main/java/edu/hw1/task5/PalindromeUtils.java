package edu.hw1.task5;

public final class PalindromeUtils {
    private PalindromeUtils() {
    }

    public static boolean isPalindromeDescendant(int number) {
        if (String.valueOf(number).length() % 2 != 0 || number < 0) {
            return false;
        }

        String tempString = String.valueOf(number);
        while (tempString.length() >= 2) {
            boolean flag = true;
            for (int i = 0; i <= tempString.length() / 2; i++) {
                if (tempString.charAt(i) != tempString.charAt(tempString.length() - 1 - i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
            StringBuilder nextString = new StringBuilder();
            for (int i = 1; i < tempString.length(); i += 2) {
                int firstDigit = tempString.charAt(i) - '0';
                int secondDigit = tempString.charAt(i - 1) - '0';
                nextString.append(firstDigit + secondDigit);
            }
            tempString = nextString.toString();
        }
        return false;
    }

}
