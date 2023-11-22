package edu.hw6.task1;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final String DELIMITER = ":";
    private final Path path;

    @SneakyThrows
    public DiskMap(Path path) {
        this.path = path;
        Files.createFile(path);
    }

    @Override
    @SneakyThrows
    public int size() {
        return Files.readAllLines(path).size();
    }

    @Override
    @SneakyThrows
    public boolean isEmpty() {
        return Files.readAllLines(path).isEmpty();
    }

    @Override
    @SneakyThrows
    public boolean containsKey(Object key) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.anyMatch(line -> line.split(DELIMITER)[0].equals(key));
        }
    }

    @Override
    @SneakyThrows
    public boolean containsValue(Object value) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.anyMatch(line -> line.split(DELIMITER)[1].equals(value));
        }
    }

    @Override
    @SneakyThrows
    public String get(Object key) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.map(line -> line.split(DELIMITER))
                .filter(keyValuePair -> keyValuePair.length > 1 && keyValuePair[0].equals(key))
                .findFirst()
                .map(keyValuePair -> keyValuePair[1])
                .orElse(null);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public String put(String key, String value) {
        String lineToWrite = key + DELIMITER + value + "\n";
        Files.writeString(path, lineToWrite, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        return lineToWrite;
    }

    @Override
    @SneakyThrows
    public String remove(Object key) {
        List<String> allLines = Files.readAllLines(path);
        allLines =
            allLines.stream().filter(line -> !line.split(DELIMITER)[0].equals(key)).collect(Collectors.toList());
        Files.write(path, allLines, StandardCharsets.UTF_8);
        return (String) key;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Set<String> keySet() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.map(line -> line.split(DELIMITER))
                .filter(keyValuePair -> keyValuePair.length > 0)
                .map(keyValuePair -> keyValuePair[0])
                .collect(Collectors.toSet());
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Collection<String> values() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.map(line -> line.split(DELIMITER))
                .filter(keyValuePair -> keyValuePair.length > 1)
                .map(keyValuePair -> keyValuePair[1])
                .collect(Collectors.toSet());
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Set<Entry<String, String>> entrySet() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.map(line -> line.split(DELIMITER))
                .filter(keyValuePair -> keyValuePair.length > 1)
                .map(keyValuePair -> Map.entry(keyValuePair[0], keyValuePair[1]))
                .collect(Collectors.toSet());
        }
    }
}
