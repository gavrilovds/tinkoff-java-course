package edu.project3.collector;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.model.FormatterComponent;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
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
                List.of(
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_1").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_2").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_3").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_3").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_1").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_5").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_6").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_7").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_8").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_10").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_11").build()).build(),
                    NginxLog.builder().request(Request.builder().resource("/downloads/product_12").build()).build()
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
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("#collect test")
    public void collect_shouldReturnRequestsStats(List<NginxLog> testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestedResourcesCollector().collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
