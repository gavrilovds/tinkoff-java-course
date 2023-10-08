package edu.hw1.task3;

import edu.hw1.task8.Pair;

public final class ArraysUtils {

    private ArraysUtils() {
    }

    public static boolean isNested(int[] internal, int[] external) {
        if (internal == null || internal.length == 0) {
            throw new IllegalArgumentException("Internal array is empty");
        }
        if (external == null || external.length == 0) {
            throw new IllegalArgumentException("External array is empty");
        }
        Pair<Integer, Integer> minAndMaxOfInternal = getMinAndMaxOfArray(internal);
        Pair<Integer, Integer> minAndMaxOfExternal = getMinAndMaxOfArray(external);
        return minAndMaxOfInternal.getSecond() < minAndMaxOfExternal.getSecond()
            && minAndMaxOfInternal.getFirst() > minAndMaxOfExternal.getFirst();
    }

    public static Pair<Integer, Integer> getMinAndMaxOfArray(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            max = Math.max(max, element);
            min = Math.min(min, element);
        }
        return new Pair<>(min, max);
    }

}
