package edu.project3.collector;

import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RequestTypesCollector extends LogStatsCollector {

    public RequestTypesCollector(LogFilter logFilter) {
        super(logFilter);
    }

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("Типы запроса")
            .tableHeaders(List.of("Запрос", "Количество"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .filter(log -> logFilter.hasPassedFilter(log))
            .map(NginxLog::request)
            .map(Request::type)
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> entry.getKey() + "|" + entry.getValue())
                    .collect(Collectors.toList())
            ));
    }
}
