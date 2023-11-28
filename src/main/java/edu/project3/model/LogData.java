package edu.project3.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record LogData(List<String> sources, OffsetDateTime from, OffsetDateTime to) {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LogData(List<String> sources, String from, String to) {
        this(
            sources,
            from == null ? null : LocalDate.parse(from, DATE_FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC),
            to == null ? null : LocalDate.parse(to, DATE_FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC)
        );
    }

    public LogData(List<String> sources) {
        this(sources, (String) null, null);
    }
}
