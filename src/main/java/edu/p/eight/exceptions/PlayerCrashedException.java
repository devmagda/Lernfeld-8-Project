package edu.p.eight.exceptions;

public class PlayerCrashedException extends Exception {
    public PlayerCrashedException() {
        super("Player crashed! Game over!");
    }

    public PlayerCrashedException(String message) {
        super(message);
    }
}