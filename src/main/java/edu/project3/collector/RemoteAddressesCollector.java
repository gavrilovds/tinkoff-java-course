package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoteAddressesCollector extends LogStatsCollector {

    private static final int ADDRESSES_LIMIT = 10;

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("%d самых частозапрашиваемых адресов".formatted(ADDRESSES_LIMIT))
            .tableHeaders(List.of("Адрес", "Количество"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(NginxLog::remoteAddress, Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> entry.getKey() + "|" + entry.getValue())
                    .limit(ADDRESSES_LIMIT)
                    .collect(Collectors.toList())
            ));
    }
}
