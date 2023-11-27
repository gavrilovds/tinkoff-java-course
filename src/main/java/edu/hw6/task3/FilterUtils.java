package edu.hw6.task3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilterUtils {

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    public static AbstractFilter magicNumber(int... bytes) {
        return path -> {
            try (InputStream inputStream = new FileInputStream(path.toFile())) {
                for (int b : bytes) {
                    if (b != inputStream.read()) {
                        return false;
                    }
                }
            }
            return true;
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
}
