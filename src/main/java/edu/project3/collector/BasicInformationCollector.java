package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.NginxLog;
import java.util.List;

public class BasicInformationCollector implements LogStatsCollector {

    @Override
    public FormatterComponent collect(List<NginxLog> logs) {
        return FormatterComponent.builder()
            .header("Общая информация")
            .tableHeaders(List.of("Метрика", "Значение"))
            .lines(null)
            .build();
    }
}
