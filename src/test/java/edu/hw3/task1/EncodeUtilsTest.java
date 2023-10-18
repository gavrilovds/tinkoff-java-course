package edu.hw3.task1;

import edu.hw3.task1.EncodeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class EncodeUtilsTest {

    @ParameterizedTest
    @CsvSource(delimiter = '➞', value = {
        "Hello world!➞Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler➞"
            + "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    @DisplayName("#encodeWithAbash default tests")
    public void message_shouldReturnEncodedWithAbashMessage(String testMessage, String expected) {
        String actual = EncodeUtils.encodeWithAtbash(testMessage);
        assertThat(actual).isEqualTo(expected);
    }

    @NullAndEmptySource
    @ParameterizedTest
    @DisplayName("#encodeWithAbash null and empty test")
    public void message_shouldThrowException_whenMessageIsNullOrEmpty(String message) {
        assertThatThrownBy(() -> EncodeUtils.encodeWithAtbash(message)).isInstanceOf(IllegalArgumentException.class);
    }
}
