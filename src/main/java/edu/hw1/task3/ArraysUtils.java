package edu.hw1.task3;

import java.util.Arrays;

public final class ArraysUtils {

    private ArraysUtils() {
    }

    public static boolean isNested(int[] array1, int[] array2) {
        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            throw new NullPointerException("Empty input");
        }
        int array1Min = Arrays.stream(array1).min().getAsInt();
        int array1Max = Arrays.stream(array1).max().getAsInt();
        int array2Min = Arrays.stream(array2).min().getAsInt();
        int array2Max = Arrays.stream(array2).max().getAsInt();
        return array1Max < array2Max && array1Min > array2Min;
    }

}
