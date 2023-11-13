package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DateParser2 extends DateParser {

    private static final String DAY_TOMORROW = "tomorrow";
    private static final String DAY_TODAY = "today";
    private static final String DAY_YESTERDAY = "yesterday";
    private static final List<String> DAYS = new ArrayList<>();

    static {
        DAYS.add(DAY_TODAY);
        DAYS.add(DAY_TOMORROW);
        DAYS.add(DAY_YESTERDAY);
    }

    @Override
    public Optional<LocalDate> parse(String string) {
        if (DAYS.contains(string)) {
            switch (string) {
                case DAY_TOMORROW -> {
                    return Optional.of(LocalDate.now().plusDays(1));
                }
                case DAY_TODAY -> {
                    return Optional.of(LocalDate.now());
                }
                default -> {
                    return Optional.of(LocalDate.now().minusDays(1));
                }
            }
        }
        return parseNext(string);
    }
}
