package edu.project3.retriever;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class UrlLogRetrieverTest {

    @Test
    @DisplayName("#retrieveLogs test")
    public void retrieveLogs_shouldReturnListWhichContainsAllLinesOfRemoteSourceFile() {
        List<String> lines = new UrlLogRetriever(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs").retrieveLogs();
        assertThat(lines).hasSize(51462);
    }
}
