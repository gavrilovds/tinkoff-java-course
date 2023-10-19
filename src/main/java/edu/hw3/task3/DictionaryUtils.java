package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DictionaryUtils {

    private DictionaryUtils() {
    }

    public static <T> Map<T, Integer> getFrequencyDictionary(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        Map<T, Integer> dictionary = new HashMap<>();
        for (var element : list) {
            dictionary.merge(element, 1, Integer::sum);
        }
        return dictionary;
    }

}
