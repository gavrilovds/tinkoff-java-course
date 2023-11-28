package edu.project3.collector;

import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import java.util.List;

public abstract class LogStatsCollector {

    protected LogFilter logFilter;

    public LogStatsCollector(LogFilter logFilter) {
        this.logFilter = logFilter;
    }

    public abstract FormatterComponent collect(LogSourceWrapper logWrapper);

    protected abstract List<String> getStatsLines(LogSourceWrapper logWrapper);
}
