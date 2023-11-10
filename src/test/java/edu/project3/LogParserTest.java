package edu.project3;

import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class LogParserTest {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private static Stream<Arguments> inputsForParseTest() {
        return Stream.of(
            Arguments.of(
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                new NginxLog(
                    "93.180.71.3",
                    "-",
                    OffsetDateTime.parse("17/May/2015:08:05:32 +0000", FORMATTER),
                    new Request(
                        "GET",
                        "/downloads/product_1",
                        "HTTP/1.1",
                        "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                    ),
                    new Response(304, 0),
                    "-"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForParseTest")
    @DisplayName("#parse test")
    public void parse_shouldReturnParsedLog(String lineToParse, NginxLog expected) {
        NginxLog actual = LogParser.parseLog(lineToParse);
        assertThat(actual).isEqualTo(expected);
    }
}
