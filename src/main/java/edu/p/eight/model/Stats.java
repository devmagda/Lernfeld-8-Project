package edu.p.eight.model;

import java.sql.Time;
import java.util.Date;
import java.util.Properties;

public class Stats {

    public static final float DEFAULT_SPEED = 0.1f;
    public static final float DEFAULT_SPAWN_RATE = 0.05f;
    public static final float DEFAULT_INCREASE_RATE = 1.1f;
    public static final boolean DEFAULT_COLLISION = true;
    public static final float DEFAULT_DECO_SPAWN_RATE = 0.1f;
    public static final long DEFAULT_ROUND_LENGTH_MS = 10000;
    public static float speed;
    public static float spawnRate;
    public static float increaseRate;
    public static boolean doCollision;

    public static int carsPassed;
    public static float decoSpawnRate;
    public static boolean dead;

    private static long timeStampLastUpdate;

    public static void init() {
        speed = DEFAULT_SPEED;
        spawnRate = DEFAULT_SPAWN_RATE;
        increaseRate = DEFAULT_INCREASE_RATE;
        doCollision = DEFAULT_COLLISION;
        carsPassed = -1;
        decoSpawnRate = DEFAULT_DECO_SPAWN_RATE;
        dead = false;
        timeStampLastUpdate = new Date().getTime();
    }

    // TODO @Anne
    public Stats fromProperties(Properties properties) {
        return null;
    }

    public static void update() {
        long distanceToLastUpdate = new Date().getTime() - timeStampLastUpdate;
        if(distanceToLastUpdate > DEFAULT_ROUND_LENGTH_MS) {
            speed = speed * (increaseRate);
            spawnRate = spawnRate * (increaseRate);
            timeStampLastUpdate = new Date().getTime();
        }
    }

    public static void print() {
        System.out.println("Speed:" + speed + "\nSpawnRate: " + spawnRate + "\nCars passed: " + carsPassed);
    }
}
