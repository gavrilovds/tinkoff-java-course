package edu.project3.collector;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RequestedResourcesCollectorTest {

    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_1").build())
                            .timeLocal(
                                OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_2").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_3").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_3").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_1").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_5").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_6").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_7").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_8").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_10").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_11").build())
                            .timeLocal(OffsetDateTime.now())
                            .build(),
                        NginxLog.builder().request(Request.builder().resource("/downloads/product_12").build())
                            .timeLocal(OffsetDateTime.now())
                            .build()
                    )
                ),
                // basic limit is 10
                List.of(
                    "'/product_1'|2",
                    "'/product_2'|1",
                    "'/product_3'|2",
                    "'/product_5'|1",
                    "'/product_6'|1",
                    "'/product_7'|1",
                    "'/product_8'|1",
                    "'/product_10'|1",
                    "'/product_11'|1",
                    "'/product_12'|1"
                )

            ));
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("#collect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual =
            new RequestedResourcesCollector(LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
