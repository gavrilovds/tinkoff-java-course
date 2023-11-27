package edu.hw6.task2;

import edu.hw6.task4.OutputStreamComposition;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.*;

public class FileUtilsTest {

    @Test
    @DisplayName("#cloneFile test")
    public void cloneFile_shouldCorrectlyCreateClonedFiles(@TempDir Path testDir) {
        Path originalFile = testDir.resolve("test.txt");
        new OutputStreamComposition().writeWithComposition(originalFile);
        FileUtils.cloneFile(originalFile);
        FileUtils.cloneFile(originalFile);
        assertThat(testDir)
            .isDirectoryContaining(path -> path.getFileName().toString().endsWith("test – копия (2).txt"))
            .isDirectoryContaining(path -> path.getFileName().toString().endsWith("test – копия (2).txt"))
            .isDirectoryContaining(path -> path.getFileName().toString().endsWith("test.txt"));
    }
}
