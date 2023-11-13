package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileUtils {

    private static final String CLONE_SUFFIX = " – копия";

    private FileUtils() {
    }

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        long num = countClonedFiles(path.getParent(), fileName.substring(0, fileName.indexOf('.')));
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        try {
            String clonedFilePath =
                path.toString().substring(0, path.toString().indexOf('.')) + CLONE_SUFFIX + (num == 0 ? fileExtension
                    : " (" + num + ")" + fileExtension);
            Files.copy(path, Path.of(clonedFilePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static long countClonedFiles(Path parentPath, String fileName) {
        try (Stream<Path> pathStream = Files.list(parentPath)) {
            return pathStream.filter(path -> path.getFileName().toString().startsWith(fileName + CLONE_SUFFIX + " ("))
                .count() + 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
