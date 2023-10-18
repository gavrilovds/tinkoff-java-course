package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public final class RomanUtils {

    private static final int MAX_NUMBER = 3999;
    private static final int MIN_NUMBER = 1;

    @SuppressWarnings("checkstyle:MagicNumber")
    private static final Map<Integer, String> COMPLIANCE_TABLE = new LinkedHashMap<>() {
        {
            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }
    };

    private RomanUtils() {
    }

    public static String convertToRoman(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("Number should be >=1 and <=3999");
        }
        int numberCopy = number;
        StringBuilder romanNumber = new StringBuilder();
        while (numberCopy > 0) {
            for (var entry : COMPLIANCE_TABLE.entrySet()) {
                while (numberCopy >= entry.getKey()) {
                    romanNumber.append(entry.getValue());
                    numberCopy -= entry.getKey();
                }
            }
        }
        return romanNumber.toString();
    }
}
