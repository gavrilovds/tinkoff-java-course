package edu.hw5.task1;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class ComputerClubUtilsTest {

    @Test
    @DisplayName("#getAverageSessionDuration basic test")
    public void getAverageSessionDuration_shouldReturnAverageSessionDuration() {
        Duration actual = ComputerClubUtils.getAverageSessionDuration(List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        ));

        assertThat(actual).hasSeconds(13200); // 3 h 40 m
    }

    @Test
    @DisplayName("#getAverageSessionDuration invalid session date")
    public void getAverageSessionDuration_shouldThrowException_whenInvalidSessionDateInList() {
        assertThatThrownBy(() -> ComputerClubUtils.getAverageSessionDuration(List.of("2123-000-23,, 20:30"))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("#getAverageSessionDuration null and empty test")
    public void getAverageSessionDuration_shouldThrowException_whenListIsNullOrEmpty(List<String> testSessions) {
        assertThatThrownBy(() -> ComputerClubUtils.getAverageSessionDuration(testSessions)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
