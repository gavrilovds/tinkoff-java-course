package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {

    private DateParser nextParser;

    public static DateParser link(DateParser first, DateParser... chain) {
        DateParser head = first;
        for (DateParser nextInChain : chain) {
            head.nextParser = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Optional<LocalDate> parse(String string);

    protected Optional<LocalDate> parseNext(String string) {
        if (nextParser == null) {
            return Optional.empty();
        }
        return nextParser.parse(string);
    }

}
