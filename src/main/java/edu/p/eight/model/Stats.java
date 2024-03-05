package edu.p.eight.model;

public class Stats {

    public static final float DEFAULT_SPEED = 5f;
    public static final float DEFAULT_SPAWN_RATE = 0.25f;
    public float speed;
    public float spawnRate;

    public Stats(float speed, float spawnRate) {
        this.speed = speed;
        this.spawnRate = spawnRate;
    }

    public Stats() {
        this(DEFAULT_SPEED, DEFAULT_SPAWN_RATE);
    }
}
