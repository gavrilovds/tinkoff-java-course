package edu.project3;

import edu.project3.collector.BasicInformationCollector;
import edu.project3.collector.LogStatsCollector;
import edu.project3.collector.RemoteAddressesCollector;
import edu.project3.collector.RequestTypesCollector;
import edu.project3.collector.RequestedResourcesCollector;
import edu.project3.collector.RequestsCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.formatter.AsciiDocLogStatsFormatter;
import edu.project3.formatter.LogStatsFormatter;
import edu.project3.formatter.MarkdownLogStatsFormatter;
import edu.project3.model.Argument;
import edu.project3.model.FormatType;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.printer.Printer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyzerApplication {

    private final List<Argument> arguments;
    private final Printer printer;
    private List<LogStatsCollector> collectors;
    private FormatType formatType = FormatType.MARKDOWN;
    private LogSourceWrapper logSourceWrapper;
    private String from;
    private String to;
    private LogFilter logFilter;

    public LogAnalyzerApplication(String[] args, Printer printer) {
        this.arguments = new ArgsResolver().resolve(args);
        this.printer = printer;
    }

    public void run() {
        initAllParameters(arguments);
        initLogFilter();
        initCollectors();
        printStats();
    }

    private void initAllParameters(List<Argument> arguments) {
        for (Argument argument : arguments) {
            switch (argument.type()) {
                case FORMAT -> {
                    formatType = FormatType.findByValue(argument.value());
                }
                case TO -> {
                    to = argument.value();
                }
                case FROM -> {
                    from = argument.value();
                }
                default -> {
                }
            }
        }
        List<String> lines = new ArrayList<>();
        String[] paths = arguments.get(0).value().split(" ");
        for (String path : paths) {
            lines.addAll(RetrieverHelper.getCorrectRetriever(path).retrieveLogs());
        }
        logSourceWrapper =
            new LogSourceWrapper(new LogData(List.of(paths), from, to), lines.stream().map(LogParser::parseLog).collect(
                Collectors.toList()));
    }

    private void initLogFilter() {
        logFilter = LogFilter.link(new DateLogFilter(to, from));
    }

    private void initCollectors() {
        collectors = new ArrayList<>();
        collectors.add(new BasicInformationCollector(logFilter));
        collectors.add(new RequestsCollector(logFilter));
        collectors.add(new RequestedResourcesCollector(logFilter));
        collectors.add(new RemoteAddressesCollector(logFilter));
        collectors.add(new RequestTypesCollector(logFilter));
    }

    private void printStats() {
        LogStatsFormatter formatter = switch (formatType) {
            case ADOC -> new AsciiDocLogStatsFormatter();
            default -> new MarkdownLogStatsFormatter();
        };
        for (LogStatsCollector collector : collectors) {
            printer.print(formatter.format(collector.collect(logSourceWrapper)));
        }
    }
}
