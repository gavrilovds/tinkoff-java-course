package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        int currentNumberOfAttempts = 0;
        boolean isExecuted = false;
        ConnectionException cause = null;
        Connection connection = manager.getConnection();
        while (currentNumberOfAttempts < maxAttempts && !isExecuted) {
            try (connection) {
                connection.execute(command);
                isExecuted = true;
            } catch (Exception e) {
                currentNumberOfAttempts++;
                cause = (ConnectionException) e;
            }
        }
        if (currentNumberOfAttempts == maxAttempts) {
            LOGGER.trace("The maximum number of attempts has been exceeded");
            throw cause;
        }
        LOGGER.trace("Command was executed successfully");
    }

    public static void main(String[] args) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 3);
        popularCommandExecutor.updatePackages();
    }
}
