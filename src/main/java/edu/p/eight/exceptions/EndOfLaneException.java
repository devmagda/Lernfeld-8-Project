package edu.p.eight.exceptions;

public class EndOfLaneException extends Exception {
    public EndOfLaneException() {
        super("Reached the end of the lane.");
    }

    public EndOfLaneException(String message) {
        super(message);
    }
}