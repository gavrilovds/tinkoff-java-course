package edu.hw2.task3;

public class ConnectionException extends RuntimeException {

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }
}
