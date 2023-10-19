package edu.hw2.task3;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PopularCommandExecutorTest {

    @Test
    @DisplayName("Wrong number of maxAttempts")
    public void shouldThrowException_whenNumberOfMaxAttemptsIsIncorrect() {
        assertThatThrownBy(() -> new PopularCommandExecutor(new DefaultConnectionManager(), -1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("tryExecute test #1")
    public void shouldThrowConnectionException_whenConnectionIsFaulty() {
        PopularCommandExecutor executor = new PopularCommandExecutor(
            new DefaultConnectionManager(new Random(78)),
            1
        );
        assertThatThrownBy(executor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("tryExecute test #2")
    public void shouldNotThrowConnectionException_whenConnectionIsStable() {
        PopularCommandExecutor executor = new PopularCommandExecutor(
            new DefaultConnectionManager(new Random(2)),
            2
        );
        assertThatCode(executor::updatePackages).doesNotThrowAnyException();
    }

}
