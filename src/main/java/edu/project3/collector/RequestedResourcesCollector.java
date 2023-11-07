package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RequestedResourcesCollector implements LogStatsCollector {

    private static final int RESOURCES_LIMIT = 10;

    @Override
    public FormatterComponent collect(List<NginxLog> logs) {
        List<String> lines = getStatsLines(logs);
        return FormatterComponent.builder()
            .header(String.format("%d самых частозапрашиваемых ресурсов", RESOURCES_LIMIT))
            .tableHeaders(List.of("Ресурс", "Количество запросов"))
            .lines(lines)
            .build();
    }

    private List<String> getStatsLines(List<NginxLog> logs) {
        return logs.stream()
            .map(NginxLog::request)
            .map(Request::resource)
            .map(str -> str.substring(str.lastIndexOf('/')))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(resource -> resource, Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> "\'" + entry.getKey() + "\'" + "|" + entry.getValue())
                    .limit(RESOURCES_LIMIT)
                    .toList()
            ));
    }
}
