package edu.project3.formatter;

import edu.project3.model.FormatterComponent;

public class MarkdownLogStatsFormatter implements LogStatsFormatter {

    @Override
    public String format(FormatterComponent formatterComponent) {
        StringBuilder formattedLog = new StringBuilder();
        return formattedLog
            .append(formatHeader(formatterComponent.header()))
            .toString();
    }

    private String formatHeader(String header) {
        return "### " + header + "\n";
    }
}
