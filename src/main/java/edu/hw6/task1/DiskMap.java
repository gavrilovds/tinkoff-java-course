package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final String DELIMITER = ":";
    private final Path path;

    public DiskMap(Path path) {
        this.path = path;
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try {
            return Files.readAllLines(path).size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            return Files.readAllLines(path).isEmpty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(DELIMITER)[0].equals(key)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(DELIMITER)[1].equals(value)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String get(Object key) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(DELIMITER);
                if (keyValuePair[0].equals(key)) {
                    return keyValuePair[1];
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String lineToWrite = key + DELIMITER + value + "\n";
        try {
            Files.writeString(path, lineToWrite, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lineToWrite;
    }

    @Override
    public String remove(Object key) {
        try {
            List<String> allLines = Files.readAllLines(path);
            allLines =
                allLines.stream().filter(line -> !line.split(DELIMITER)[0].equals(key)).collect(Collectors.toList());
            Files.write(path, allLines, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (String) key;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try {
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(DELIMITER);
                keys.add(keyValuePair[0]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return keys;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Set<String> values = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(DELIMITER);
                values.add(keyValuePair[1]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> keyValueEntries = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(DELIMITER);
                keyValueEntries.add(Map.entry(keyValuePair[0], keyValuePair[1]));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return keyValueEntries;
    }
}
