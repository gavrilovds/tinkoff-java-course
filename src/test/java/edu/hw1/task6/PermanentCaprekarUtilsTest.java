package edu.hw1.task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
public class PermanentCaprekarUtilsTest {

    @ParameterizedTest
    @CsvSource({
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "-1234, -1",
        "10001, -1",
        "8888, -1"
    })
    public void number_shouldReturnNumberOfStepsToGetACarperkarConst_whenInputIsCorrect(int testNumber, int expected){
        int actual = PermanentCaprekarUtils.countK(testNumber);
        assertThat(actual).isEqualTo(expected);
    }

}
