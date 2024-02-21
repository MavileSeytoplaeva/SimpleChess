package org.example;

public class NoMoreMovesException extends RuntimeException {
    public NoMoreMovesException(String message) {
        super(message);
    }
}
