package edu.project3.collector;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RequestTypesCollectorTest {
    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().request(Request.builder().type("GET").build()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).build(),
                        NginxLog.builder().request(Request.builder().type("PUT").build()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE").build()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE").build()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH").build()).build()
                    )
                ), List.of("GET|4", "PUT|1", "DELETE|2", "PATCH|1")
            ));
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("#collect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestTypesCollector().collect(testLogs);
        System.out.println(actual.lines());
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
