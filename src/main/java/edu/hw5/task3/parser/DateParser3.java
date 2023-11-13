package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser3 extends DateParser {

    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d+)\\s+day(s?)\\s+ago");

    @Override
    public Optional<LocalDate> parse(String string) {
        Matcher dateMatcher = DATE_PATTERN.matcher(string);
        if (dateMatcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(dateMatcher.group(1))));
        } else {
            return parseNext(string);
        }
    }
}
