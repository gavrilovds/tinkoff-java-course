package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Number of max attempts should be >0");
        }
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        int currentNumberOfAttempts = 0;
        ConnectionException cause = null;
        Connection connection = manager.getConnection();
        while (currentNumberOfAttempts < maxAttempts) {
            try (connection) {
                connection.execute(command);
                LOGGER.info("Command was executed successfully");
                return;
            } catch (Exception e) {
                currentNumberOfAttempts++;
                if (e instanceof ConnectionException) {
                    cause = (ConnectionException) e;
                }
            }
        }
        if (currentNumberOfAttempts == maxAttempts) {
            throw new ConnectionException("The maximum number of attempts has been exceeded", cause);
        }
    }
}
