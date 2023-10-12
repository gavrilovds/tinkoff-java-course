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
    @DisplayName("FaultyConnectionManager#getConnection test")
    public void shouldAlwaysReturnFaultyConnection_whenUseFaultyConnectionManager() {
        ConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        Connection actual = faultyConnectionManager.getConnection();
        assertThat(actual).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("DefaultConnectionManager#getConnection with StableConnection")
    public void shouldReturnStableConnection_whenConnectionWasSuccess() {
        // using seed 2, to prove that DefaultConnectionManager can return StableConnection
        Random random = new Random(2);
        ConnectionManager connectionManager = new DefaultConnectionManager(random);
        Connection actual = connectionManager.getConnection();
        assertThat(actual).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("DefaultConnectionManager# with FaultyConnection")
    public void shouldReturnFaultyConnection_whenConnectionWasFailed() {
        // using seed 1, to prove that DefaultConnectionManager can return FaultyConnection
        Random random = new Random(1);
        ConnectionManager connectionManager = new DefaultConnectionManager(random);
        Connection actual = connectionManager.getConnection();
        assertThat(actual).isInstanceOf(FaultyConnection.class);
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
