package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    private static final int CHANCE_OF_FAULTY_CONNECTION = 5;
    private final Random random;

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    public DefaultConnectionManager() {
        this.random = new Random();
    }

    @Override
    public Connection getConnection() {
        if (random.nextInt(CHANCE_OF_FAULTY_CONNECTION) == 0) {
            return new FaultyConnection(random);
        }
        return new StableConnection();
    }
}
