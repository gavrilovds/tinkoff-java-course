package edu.project3;

import edu.project3.printer.CLIPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogAnalyzerApplicationTest {

    @Test
    @DisplayName("#run test")
    public void run_shouldNotThrowAnyException_whenEverythingIsRight() {
        Assertions.assertDoesNotThrow(() -> new LogAnalyzerApplication(new String[] {"--path",
            "logs/generated_logs/log1.txt",
            "logs/generated_logs/log2.txt",
            "--format",
            "markdown", "--from", "2023-03-22", "--to", "2023-03-26" }, new CLIPrinter()).run());
    }
}
