package edu.project3.collector;

import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BasicInformationCollector extends LogStatsCollector {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public BasicInformationCollector(LogFilter logFilter) {
        super(logFilter);
    }

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("Общая информация")
            .tableHeaders(List.of("Метрика", "Значение"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        StringBuilder metaData = new StringBuilder();
        metaData.append("Файл(-ы)|");
        for (String source : logWrapper.logData().sources()) {
            metaData.append("'").append(source).append("'");
        }
        metaData
            .append("\n")
            .append("Начальная дата|")
            .append(logWrapper.logData().from() == null ? "-" : DATE_FORMATTER.format(logWrapper.logData().from()))
            .append("\n")
            .append("Конечная дата|")
            .append(logWrapper.logData().to() == null ? "-" : DATE_FORMATTER.format(logWrapper.logData().to()))
            .append("\n")
            .append("Количество запросов|")
            .append(getTotalAmountOfLogs(logWrapper.logs()))
            .append("\n")
            .append("Средний размер ответа|")
            .append(getAvgResponseSize(logWrapper.logs()))
            .append("b");
        return List.of(metaData.toString().split("\n"));
    }

    private double getAvgResponseSize(List<NginxLog> logs) {
        double total = logs.stream().filter(log -> logFilter.hasPassedFilter(log)).map(NginxLog::response)
            .mapToDouble(Response::bodyBytesSend).sum();
        return total / logs.size();
    }

    private long getTotalAmountOfLogs(List<NginxLog> logs) {
        return logs.stream().filter(log -> logFilter.hasPassedFilter(log)).count();
    }
}
