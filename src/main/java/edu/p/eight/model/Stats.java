package edu.p.eight.model;

import java.util.Properties;

public class Stats {

    public static final float DEFAULT_SPEED = 0.1f;
    public static final float DEFAULT_SPAWN_RATE = 0.05f;
    public static final float DEFAULT_INCREASE_RATE = 2f;
    public static final boolean DEFAULT_COLLISION = true;
    public static final float DEFAULT_DECO_SPAWN_RATE = 0.1f;
    public static float speed;
    public static float spawnRate;
    public static float increaseRate;
    public static boolean doCollision;

    public static int carsPassed;
    public static float decoSpawnRate;

    public static void init() {
        speed = DEFAULT_SPEED;
        spawnRate = DEFAULT_SPAWN_RATE;
        increaseRate = DEFAULT_INCREASE_RATE;
        doCollision = DEFAULT_COLLISION;
        carsPassed = -1;
        decoSpawnRate = DEFAULT_DECO_SPAWN_RATE;
    }

    // TODO @Anne
    public Stats fromProperties(Properties properties) {
        return null;
    }

    public static void update() {
        if(carsPassed+2%10 == 0) {
            speed = speed * (increaseRate);
            spawnRate = spawnRate * (increaseRate);
        }
    }
}
