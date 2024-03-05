package edu.p.eight.exceptions;

public class SpawningFailedException extends Exception {
    public SpawningFailedException() {
        super("Spawning of entity has Failed!");
    }

    public SpawningFailedException(String message) {
        super(message);
    }
}