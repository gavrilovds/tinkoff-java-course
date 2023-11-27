package edu.hw6.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtils {

    private static final String CLONE_SUFFIX = " – копия";

    @SneakyThrows
    public static void cloneFile(Path path) {
        String fileNameWithExtension = path.getFileName().toString();
        int extensionPointIndex = fileNameWithExtension.indexOf('.');
        String fileName = fileNameWithExtension.substring(0, extensionPointIndex);
        String fileExtension = fileNameWithExtension.substring(extensionPointIndex);
        boolean isFileCreated = false;
        int currentCopy = 1;
        while (!isFileCreated) {
            String newFileName = path.getParent().toString() + "/" + fileName + CLONE_SUFFIX + (currentCopy == 1 ? ""
                : " (" + currentCopy + ")") + fileExtension;
            if (Files.exists(Path.of(newFileName))) {
                currentCopy++;
            } else {
                Files.copy(path, Path.of(newFileName));
                return;
            }
        }
    }
}



