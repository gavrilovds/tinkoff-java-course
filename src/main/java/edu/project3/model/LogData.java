package edu.project3.model;

import java.time.OffsetDateTime;
import java.util.List;

public record LogData(List<String> sources, OffsetDateTime from, OffsetDateTime to) {

    public LogData(List<String> sources) {
        this(sources, null, null);
    }
}
