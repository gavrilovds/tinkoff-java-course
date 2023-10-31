package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser5 extends DateParser {

    private static final String DATE_TOMORROW = "tomorrow";

    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.equals(DATE_TOMORROW)) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else {
            return parseNext(string);
        }
    }
}
