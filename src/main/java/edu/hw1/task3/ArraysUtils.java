package edu.hw1.task3;

import java.util.Arrays;

public final class ArraysUtils {

    private ArraysUtils() {
    }

    public static boolean isNested(int[] internal, int[] external) {
        if (internal == null || external == null || internal.length == 0 || external.length == 0) {
            throw new IllegalArgumentException("Empty input");
        }
        int internalMin = Arrays.stream(internal).min().getAsInt();
        int internalMax = Arrays.stream(internal).max().getAsInt();
        int externalMin = Arrays.stream(external).min().getAsInt();
        int externalMax = Arrays.stream(external).max().getAsInt();
        return internalMax < externalMax && internalMin > externalMin;
    }

}
