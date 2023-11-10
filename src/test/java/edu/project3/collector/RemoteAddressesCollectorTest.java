package edu.project3.collector;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RemoteAddressesCollectorTest {

    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.253.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.253.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("192.73.252.145|4", "192.73.253.145|2")
            ));
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("#collect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RemoteAddressesCollector(LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
