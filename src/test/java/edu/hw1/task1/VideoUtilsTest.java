package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VideoUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "06:77, -1",
        "34:43:23, -1",
        "aa:23, -1",
        "23:aa, -1",
        "01:1, -1",
        "999:59, 59999",
        "13:56, 836"
    })
    @DisplayName("Тестирование minuteToSeconds")
    public void testTime_shouldReturnCorrectAnswer_whenInputIsCorrect(String testTime, int expected) {
        int actual = VideoUtils.minuteToSeconds(testTime);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Тестирование minuteToSeconds на пустых входных данных")
    public void testTime_shouldThrowException_whenInputIsNullOrEmpty(String testString) {
        assertThatThrownBy(() -> {
            int actual = VideoUtils.minuteToSeconds(testString);
        }).isInstanceOf(NullPointerException.class);
    }

}
