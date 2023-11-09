package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RequestedResourcesCollector extends LogStatsCollector {

    private static final int RESOURCES_LIMIT = 10;

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("%d самых частозапрашиваемых ресурсов".formatted(RESOURCES_LIMIT))
            .tableHeaders(List.of("Ресурс", "Количество запросов"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .map(NginxLog::request)
            .map(Request::resource)
            .map(str -> str.substring(str.lastIndexOf('/')))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> "\'" + entry.getKey() + "\'" + "|" + entry.getValue())
                    .limit(RESOURCES_LIMIT)
                    .toList()
            ));
    }
}
