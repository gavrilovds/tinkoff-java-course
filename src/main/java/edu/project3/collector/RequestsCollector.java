package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.HttpStatusCode;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.util.List;
import java.util.stream.Collectors;

public class RequestsCollector implements LogStatsCollector {

    @Override
    public FormatterComponent collect(List<NginxLog> logs) {
        List<String> lines = getStatsLines(logs);

        return FormatterComponent.builder()
            .header("Коды ответа")
            .tableHeaders(List.of("Код", "Имя", "Количество"))
            .lines(lines)
            .build();
    }

    private List<String> getStatsLines(List<NginxLog> logs) {
        return logs.stream()
            .map(NginxLog::response)
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Response::statusCode, Collectors.counting()),
                map -> map.entrySet().stream()
                    .map(entry -> HttpStatusCode.getByValue(entry.getKey()) + "|"
                        + entry.getValue())
                    .sorted()
                    .collect(Collectors.toList()).reversed()
            ));
    }
}
