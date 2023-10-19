package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CHANCE_OF_CONNECTION_ERROR = 2;
    private final Random random;

    public FaultyConnection(Random random) {
        this.random = random;
    }

    public FaultyConnection() {
        this(new Random());
    }

    @Override
    public void execute(String command) {
        if (random.nextInt(CHANCE_OF_CONNECTION_ERROR) == 1) {
            throw new ConnectionException("Connection error");
        }
        //connection has not been lost, so continue execution
    }

    @Override
    public void close() {
        LOGGER.info("Connection closed");
    }
}
