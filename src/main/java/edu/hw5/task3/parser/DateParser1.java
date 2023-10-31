package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateParser1 extends DateParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Optional<LocalDate> parse(String string) {
        try {
            return Optional.of(LocalDate.parse(string, DATE_TIME_FORMATTER));
        } catch (DateTimeParseException e) {
            return parseNext(string);
        }
    }
}
