package edu.hw6.task4;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.*;

public class OutputStreamCompositionTest {

    @Test
    @DisplayName("#writeWithComposition test")
    public void writeWithComposition_shouldWriteMessageToFileUsingOutputStreamComposition(@TempDir Path testDir) {
        Path testFile = testDir.resolve("test.txt");
        new OutputStreamComposition().writeWithComposition(testFile);
        assertThat(testFile).hasContent("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
