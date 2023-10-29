package edu.hw3.task7;

import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class TreeMapComparatorWithNullTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("Put null as key test")
    public void nullableKey_shouldBeInTreeMap(String nullableKey) {
        Map<String, String> treeMap = new TreeMap<>(new TreeMapComparatorWithNull());
        treeMap.put(nullableKey, "test");
        assertThat(treeMap).contains(entry(null, "test"));
    }

}
