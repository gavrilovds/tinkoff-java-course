package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public final class FilterUtils {

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    private FilterUtils() {
    }

    public static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                return startsWith(fileBytes, bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> path.getParent().getFileSystem().getPathMatcher("glob:" + glob)
            .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile(regex).matcher(path.toString()).find();
    }

    private static boolean startsWith(byte[] array, byte... prefix) {
        if (array.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (array[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }
}
