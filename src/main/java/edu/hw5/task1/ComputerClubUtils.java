package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComputerClubUtils {

    private static final Pattern SESSION_PATTERN =
        Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
    private static final DateTimeFormatter SESSION_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private ComputerClubUtils() {
    }

    public static Duration getAverageSessionDuration(List<String> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            throw new IllegalArgumentException("Empty session list");
        }
        Duration totalDuration = Duration.ZERO;
        for (String session : sessions) {
            Matcher sessionMatcher = SESSION_PATTERN.matcher(session);
            if (sessionMatcher.find()) {
                LocalDateTime startTime = LocalDateTime.parse(sessionMatcher.group(1), SESSION_DATE_FORMATTER);
                LocalDateTime endTime = LocalDateTime.parse(sessionMatcher.group(2), SESSION_DATE_FORMATTER);
                totalDuration = totalDuration.plus(Duration.between(startTime, endTime));
            } else {
                throw new IllegalArgumentException("Wrong session date format");
            }
        }
        return Duration.ofSeconds(totalDuration.getSeconds() / sessions.size());
    }
}
