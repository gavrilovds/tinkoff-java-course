package edu.project3.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record LogData(List<String> sources, OffsetDateTime from, OffsetDateTime to) {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LogData(List<String> sources, String from, String to) {
        this(sources, from == null ? null : (OffsetDateTime) DATE_FORMATTER.parse(from),
            to == null ? null : (OffsetDateTime) DATE_FORMATTER.parse(to)
        );
    }

    public LogData(List<String> sources) {
        this(sources, (String) null,  null);
    }
}
