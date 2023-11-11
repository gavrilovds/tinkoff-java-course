package edu.project3.filter;

import edu.project3.model.NginxLog;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateLogFilter extends LogFilter {

    private final OffsetDateTime from;
    private final OffsetDateTime to;

    public DateLogFilter(String from, String to) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.from = from == null ? OffsetDateTime.MIN : LocalDate.parse(from, formatter).atStartOfDay().atOffset(
                ZoneOffset.UTC);
            this.to = to == null ? OffsetDateTime.MAX
                : LocalDate.parse(to, formatter).atStartOfDay().atOffset(ZoneOffset.UTC);
        } catch (Exception e) {
            throw new IllegalArgumentException("wrong date format, use \"yyyy-MM-dd\"");
        }
    }

    @Override
    public boolean hasPassedFilter(NginxLog log) {
        if (!(log.timeLocal().isAfter(from) && log.timeLocal().isBefore(to))) {
            return false;
        }
        return checkNext(log);
    }
}
