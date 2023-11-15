package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {

    private static final String CLONE_SUFFIX = " – копия";

    private FileUtils() {
    }

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        fileName = fileName.substring(0, fileName.indexOf('.'));
        boolean isFileCreated = false;
        int currentCopy = 1;
        while (!isFileCreated) {
            String newFileName = path.getParent().toString() + "/" + fileName + CLONE_SUFFIX + (currentCopy == 1 ? ""
                : " (" + currentCopy + ")") + fileExtension;
            if (Files.exists(Path.of(newFileName))) {
                currentCopy++;
            } else {
                try {
                    Files.copy(path, Path.of(newFileName));
                    isFileCreated = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
