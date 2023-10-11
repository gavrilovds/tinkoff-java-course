package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PopularCommandExecutorTest {

    @Test
    @DisplayName("Тест с FaultyConnection")
    public void test() {
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        assertThatThrownBy(executor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Тест с StableConnection")
    public void executor_shouldNotThrowExceptionWhenConnectionIsStable() {
        PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 5);
        assertThatCode(executor::updatePackages).doesNotThrowAnyException();
    }

}
