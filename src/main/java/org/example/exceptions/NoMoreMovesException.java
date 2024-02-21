package org.example.exceptions;

public class NoMoreMovesException extends RuntimeException {
    public NoMoreMovesException(String message) {
        super(message);
    }
}
