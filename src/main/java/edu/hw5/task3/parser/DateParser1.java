package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser1 extends DateParser {

    private static final Pattern FIRST_TYPE_DATE = Pattern.compile("^(\\d{4})(-)(\\d{2})-(\\d{1,2})$");

    private static final Pattern SECOND_TYPE_DATE = Pattern.compile("^(\\d{1})(/)(\\d{1})/(\\d{2,4})$");
    private final List<Pattern> allTypes =
        List.of(FIRST_TYPE_DATE, SECOND_TYPE_DATE);

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Optional<LocalDate> parse(String string) {
        for (Pattern pattern : allTypes) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                switch (matcher.group(2)) {
                    case "-" -> {
                        return Optional.of(LocalDate.of(
                            Integer.parseInt(matcher.group(1)),
                            Integer.parseInt(matcher.group(3)),
                            Integer.parseInt(matcher.group(4))
                        ));
                    }
                    default -> {
                        return Optional.of(LocalDate.of(
                            Integer.parseInt(
                                matcher.group(4).length() != 4 ? "20" + matcher.group(4) : matcher.group(4)),
                            Integer.parseInt(matcher.group(3)),
                            Integer.parseInt(matcher.group(1))
                        ));
                    }
                }
            }
        }
        return parseNext(string);
    }
}
