package edu.hw6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.*;

public class DiskMapTest {

    @TempDir(cleanup = CleanupMode.ALWAYS)
    Path testDir;
    Path storage;
    DiskMap diskMap;

    @BeforeEach
    public void initStorage() {
        storage = testDir.resolve("data_storage.txt");
        diskMap = new DiskMap(storage);
    }

    @Test
    @DisplayName("#size test")
    public void size_shouldReturnNumberOfLinesInFile() {
        diskMap.put("test", "rqwr");
        int actual = diskMap.size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("#isEmpty test")
    public void isEmpty_shouldReturnFalse_whenStorageFileIsNotEmpty() {
        diskMap.put("tewt", "rew");
        boolean actual = diskMap.isEmpty();
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("#containsKey test")
    public void containsKey_shouldReturnTrue_whenGivenKeyIsPresentedInFile() {
        String testKey = "kek";
        diskMap.put(testKey, "kekw");
        boolean actual = diskMap.containsKey(testKey);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("#containsValue test")
    public void containsValue_shouldReturnTrue_whenGivenValueIsPresentedInFile() {
        String testValue = "pump";
        diskMap.put("dddd", testValue);
        boolean actual = diskMap.containsValue(testValue);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("#get test")
    public void get_shouldReturnValueOfGivenKey() {
        String key = "test";
        String value = "val";
        diskMap.put(key, value);
        String actual = diskMap.get(key);
        assertThat(actual).isEqualTo(value);
    }

    @Test
    @DisplayName("#put test")
    public void put_shouldAddKeyAndValueIntoFile() {
        String key = "test";
        String value = "val";
        diskMap.put(key, value);
        assertThat(storage).hasContent("test:val");
    }

    @Test
    @DisplayName("#remove test")
    public void remove_shouldDeleteLineWithGivenKeyFromFile() {
        diskMap.put("test", "pamparam");
        diskMap.put("xd", "ldlsld");
        diskMap.remove("test");
        assertThat(storage).hasContent("xd:ldlsld");
    }

    @Test
    @DisplayName("#putAll test")
    public void putAll_shouldAddAllKeyValuePairsIntoFile() throws IOException {
        Map<String, String> testKeyValues = new LinkedHashMap<>();
        testKeyValues.put("test1", "tinkoff");
        testKeyValues.put("test2", "nice");
        diskMap.putAll(testKeyValues);
        System.out.println(Files.readAllLines(storage));
        assertThat(storage).hasContent("test1:tinkoff\ntest2:nice");
    }

    @Test
    @DisplayName("#clear test")
    public void clear_shouldClearFile() {
        diskMap.put("test", "wer");
        diskMap.clear();
        assertThat(storage).isEmptyFile();
    }

    @Test
    @DisplayName("#keySet test")
    public void keySet_shouldReturnSetOfKeysPresentedInFile() {
        Map<String, String> testKeyValues = new LinkedHashMap<>();
        testKeyValues.put("test1", "tinkoff");
        testKeyValues.put("test2", "nice");
        diskMap.putAll(testKeyValues);
        Set<String> actual = diskMap.keySet();
        assertThat(actual).containsExactlyInAnyOrder("test1", "test2");
    }

    @Test
    @DisplayName("#values test")
    public void keyValue_shouldReturnSetOfValuesPresentedInFile() {
        Map<String, String> testKeyValues = new LinkedHashMap<>();
        testKeyValues.put("test1", "tinkoff");
        testKeyValues.put("test2", "nice");
        diskMap.putAll(testKeyValues);
        Collection<String> actual = diskMap.values();
        assertThat(actual).containsExactlyInAnyOrder("tinkoff", "nice");
    }

    @Test
    @DisplayName("#entrySet test")
    public void entry_shouldReturnSetOfEntriesPresentedInFile() {
        Map<String, String> testKeyValues = new LinkedHashMap<>();
        testKeyValues.put("test1", "tinkoff");
        testKeyValues.put("test2", "nice");
        diskMap.putAll(testKeyValues);
        Set<Entry<String, String>> actual = diskMap.entrySet();
        assertThat(actual).containsExactlyInAnyOrder(entry("test1", "tinkoff"), entry("test2", "nice"));
    }
}
