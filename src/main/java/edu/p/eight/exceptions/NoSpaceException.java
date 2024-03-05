package edu.p.eight.exceptions;

public class NoSpaceException extends Exception {
    public NoSpaceException() {
        super("No space to spawn available");
    }

    public NoSpaceException(String message) {
        super(message);
    }
}