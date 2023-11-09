package edu.project3.collector;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BasicInformationCollectorTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test.log")),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(200).build()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(400).build()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test.log'",
                    "Начальная дата|-",
                    "Конечная дата|-",
                    "Количество запросов|2",
                    "Средний размер ответа|300.0b"
                )
            ),
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test1.log", "test2.log"), OffsetDateTime.now(), OffsetDateTime.now()),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(200).build()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(400).build()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test1.log''test2.log'",
                    "Начальная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Конечная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Количество запросов|2",
                    "Средний размер ответа|300.0b"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("#collect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new BasicInformationCollector().collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
