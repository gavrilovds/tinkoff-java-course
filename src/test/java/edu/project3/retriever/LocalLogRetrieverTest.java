package edu.project3.retriever;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LocalLogRetrieverTest {

    @Test
    @DisplayName("#retrieveLogs test")
    public void retrieveLogs_shouldReturnListWhichContainsAllLinesOfLogFile() {
        List<String> lines = new LocalLogRetriever("logs/generated_logs/log1.txt").retrieveLogs();
        assertThat(lines).hasSize(252);
    }
}
