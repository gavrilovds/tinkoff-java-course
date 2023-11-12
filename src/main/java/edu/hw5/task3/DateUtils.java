package edu.hw5.task3;

import edu.hw5.task3.parser.DateParser;
import edu.hw5.task3.parser.DateParser1;
import edu.hw5.task3.parser.DateParser2;
import edu.hw5.task3.parser.DateParser3;
import java.time.LocalDate;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public final class DateUtils {

    private static final DateParser PARSER_CHAIN = DateParser.link(
        new DateParser1(),
        new DateParser2(),
        new DateParser3()
    );

    private DateUtils() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        if (StringUtils.isBlank(string)) {
            throw new IllegalArgumentException("string should not be empty or null");
        }
        return PARSER_CHAIN.parse(string);
    }
}
