package edu.p.eight.model;

import java.util.Properties;

public class Stats {

    public static final float DEFAULT_SPEED = 0.05f;
    public static final float DEFAULT_SPAWN_RATE = 0.05f;
    public static final float DEFAULT_INCREASE_RATE = 0.05f;
    public static final boolean DEFAULT_COLLISION = false;
    public static float speed;
    public static float spawnRate;
    public static float increaseRate;
    public static boolean doCollision;

    public static void init() {
        speed = DEFAULT_SPEED;
        spawnRate = DEFAULT_SPAWN_RATE;
        increaseRate = DEFAULT_INCREASE_RATE;
        doCollision = DEFAULT_COLLISION;
    }

    // TODO @Anne
    public Stats fromProperties(Properties properties) {
        return null;
    }
}
