package edu.project3.formatter;

import edu.project3.model.FormatterComponent;

public interface LogStatsFormatter {

    String SEPARATOR = "\\|";

    String format(FormatterComponent formatterComponent);
}
