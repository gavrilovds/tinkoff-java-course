package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    private static final int BOUND = 5;

    @Override
    public Connection getConnection() {
        //chance to get a FaultyConnection is 1/BOUND
        if (new Random().nextInt(1, BOUND) == 1) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
