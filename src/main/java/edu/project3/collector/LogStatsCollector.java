package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.LogSourceWrapper;
import java.util.List;

public abstract class LogStatsCollector {

    public abstract FormatterComponent collect(LogSourceWrapper logWrapper);

    protected abstract List<String> getStatsLines(LogSourceWrapper logWrapper);
}
