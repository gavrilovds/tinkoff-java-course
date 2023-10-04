package edu.hw1.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @ParameterizedTest
    @CsvSource(delimiter = '➞', value = {
        "123456➞214365",
        "hTsii  s aimex dpus rtni.g➞This is a mixed up string.",
        "badce➞abcde"
    })
    @DisplayName("Тестирование fixString")
    public void string_shouldReturnFixedString(String testString, String expected) {
        String actual = StringUtils.fixString(testString);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Тестирование fixString на пустых входных данных")
    public void string_ShouldReturnEmptyString(String testString){
        String actual = StringUtils.fixString(testString);
        assertThat(actual).isEqualTo("");
    }
}
