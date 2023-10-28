package edu.hw3.task4;

public final class RomanUtils {

    private static final int MAX_NUMBER = 3999;
    private static final int MIN_NUMBER = 1;

    private RomanUtils() {
    }

    public static String convertToRoman(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("Number should be >= 1 and <= 3999");
        }
        int numberCopy = number;
        StringBuilder romanNumber = new StringBuilder();
        while (numberCopy > 0) {
            for (var entry : RomanComplianceTable.values()) {
                while (numberCopy >= entry.arabNumber) {
                    romanNumber.append(entry);
                    numberCopy -= entry.arabNumber;
                }
            }
        }
        return romanNumber.toString();
    }

    private enum RomanComplianceTable {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);
        private final int arabNumber;

        RomanComplianceTable(int arabNumber) {
            this.arabNumber = arabNumber;
        }

        public int getArabNumber() {
            return arabNumber;
        }
    }
}
