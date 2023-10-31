package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser6 extends DateParser {

    private static final String DATE_TODAY = "today";

    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.equals(DATE_TODAY)) {
            return Optional.of(LocalDate.now());
        } else {
            return parseNext(string);
        }
    }
}
