package edu.project3.formatter;

import edu.project3.model.FormatterComponent;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class AsciiDocLogStatsFormatterTest {

    private static Stream<Arguments> inputsForFormatTest() {
        return Stream.of(
            Arguments.of(
                new FormatterComponent(
                    "Общая информация",
                    List.of("Метрика", "Значение"),
                    List.of(
                        "Файл(-ы)|'test.log'",
                        "Начальная дата|-",
                        "Конечная дата|-",
                        "Количество запросов|2",
                        "Средний размер ответа|300.0b"
                    )
                ),
                """
                    === Общая информация
                    |================================
                    |              Метрика|  Значение

                    |             Файл(-ы)|'test.log'
                    |       Начальная дата|         -
                    |        Конечная дата|         -
                    |  Количество запросов|         2
                    |Средний размер ответа|    300.0b
                    |================================
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForFormatTest")
    @DisplayName("#format test")
    public void format_shouldReturnFormattedStringInAsciiDocFormat(FormatterComponent toFormat, String expected) {
        String actual = new AsciiDocLogStatsFormatter().format(toFormat);
        assertThat(actual).isEqualTo(expected);
    }
}
