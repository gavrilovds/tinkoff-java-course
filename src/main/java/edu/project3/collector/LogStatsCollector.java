package edu.project3.collector;

import edu.project3.model.FormatterComponent;
import edu.project3.model.NginxLog;
import java.util.List;

public interface LogStatsCollector {

    FormatterComponent collect(List<NginxLog> logs);
}
