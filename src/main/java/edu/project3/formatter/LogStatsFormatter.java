package edu.project3.formatter;

import edu.project3.model.FormatterComponent;
import java.util.List;

public abstract class LogStatsFormatter {

    protected final static String SEPARATOR = "\\|";

    public abstract String format(FormatterComponent formatterComponent);

    protected abstract String formatHeader(String header);

    protected abstract String formatTableHeaders(List<String> tableHeader, int[] columnMaxWidth);

    protected abstract String formatTableRows(List<String> tableRows, int[] columnMaxWidth);

    protected void calculateColumnMaxWidth(int[] columnMaxWidth, FormatterComponent formatterComponent) {
        for (String line : formatterComponent.lines()) {
            String[] separatedLine = line.split(SEPARATOR);
            for (int i = 0; i < separatedLine.length; i++) {
                columnMaxWidth[i] = Math.max(columnMaxWidth[i], separatedLine[i].length());
            }
        }
        for (int i = 0; i < formatterComponent.tableHeaders().size(); i++) {
            columnMaxWidth[i] = Math.max(columnMaxWidth[i], formatterComponent.tableHeaders().get(i).length());
        }
    }
}
