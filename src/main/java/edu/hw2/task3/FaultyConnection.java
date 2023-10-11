package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BOUND = 3;

    @Override
    public void execute(String command) {
        if (new Random().nextInt(1, BOUND) == 1) {
            //connection is lost in chance 1/BOUND
            throw new ConnectionException();
        }
        //connection isn`t lost, so continue execution
    }

    @Override
    public void close() throws Exception {
        LOGGER.trace("Connection closed");
    }
}
