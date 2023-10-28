package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DictionaryUtils {

    private DictionaryUtils() {
    }

    public static <T> Map<T, Long> getFrequencyDictionary(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        return list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

}
