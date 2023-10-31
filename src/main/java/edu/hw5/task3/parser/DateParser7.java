package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser7 extends DateParser {

    private static final String DATE_YESTERDAY = "yesterday";

    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.equals(DATE_YESTERDAY)) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else {
            return parseNext(string);
        }
    }
}
