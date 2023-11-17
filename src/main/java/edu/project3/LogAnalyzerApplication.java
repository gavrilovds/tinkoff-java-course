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
import edu.project3.model.TimeInterval;
import edu.project3.printer.CLIPrinter;
import edu.project3.printer.Printer;
import edu.project3.retriever.selector.LocalRetrieverSelector;
import edu.project3.retriever.LogRetriever;
import edu.project3.retriever.selector.RetrieverSelector;
import edu.project3.retriever.selector.UrlRetrieverSelector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyzerApplication {

    private final List<Argument> arguments;
    private final Printer printer;
    private List<LogStatsCollector> collectors;
    private FormatType formatType = FormatType.MARKDOWN;
    private LogSourceWrapper logSourceWrapper;
    private TimeInterval timeInterval;
    private LogFilter logFilter;

    public LogAnalyzerApplication(String[] args) {
        this.arguments = new ArgsResolver().resolve(args);
        this.printer = new CLIPrinter();
    }

    public void run() {
        initAllParameters();
        // arguments(0).value() is always paths
        loadAllLogsToWrapper(arguments.get(0).value().split(" "));
        initLogFilter();
        initCollectors();
        printStats();
    }

    private void initAllParameters() {
        TimeInterval.TimeIntervalBuilder timeIntervalBuilder = TimeInterval.builder();
        for (Argument argument : arguments) {
            switch (argument.type()) {
                case FORMAT -> {
                    formatType = FormatType.findByValue(argument.value());
                }
                case TO -> {
                    timeIntervalBuilder.to(argument.value());
                }
                case FROM -> {
                    timeIntervalBuilder.from(argument.value());
                }
                default -> {
                }
            }
        }
        timeInterval = timeIntervalBuilder.build();
    }

    private void loadAllLogsToWrapper(String[] paths) {
        List<String> lines = new ArrayList<>();
        for (String path : paths) {
            LogRetriever logRetriever = getRetriever(path);
            lines.addAll(logRetriever.retrieveLogs());
        }
        logSourceWrapper =
            new LogSourceWrapper(
                new LogData(List.of(paths), timeInterval.from(), timeInterval.to()),
                lines.stream().map(LogParser::parseLog).collect(
                    Collectors.toList())
            );
    }

    private LogRetriever getRetriever(String path) {
        RetrieverSelector selector = RetrieverSelector.link(new LocalRetrieverSelector(), new UrlRetrieverSelector());
        return selector.selectRetriever(path);
    }

    private void initLogFilter() {
        logFilter = LogFilter.link(new DateLogFilter(timeInterval.to(), timeInterval.from()));
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
