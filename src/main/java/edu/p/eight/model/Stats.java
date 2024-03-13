package edu.p.eight.model;

public class Stats {

    public static final float DEFAULT_SPEED = 5f;
    public static final float DEFAULT_SPAWN_RATE = 0.25f;
    public static final float DEFAULT_INCREASE_RATE = 0.005f;
    public static float speed;
    public static float spawnRate;
    public static float increaseRate;

    public Stats(float speed, float spawnRate, float increaseRate) {
        this.speed = speed;
        this.spawnRate = spawnRate;
        this.increaseRate = increaseRate;
    }

    public Stats() {
        this(DEFAULT_SPEED, DEFAULT_SPAWN_RATE, DEFAULT_INCREASE_RATE);
    }
}
