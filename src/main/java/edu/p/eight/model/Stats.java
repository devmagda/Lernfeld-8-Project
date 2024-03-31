package edu.p.eight.model;

import edu.p.eight.utils.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;

public class Stats {

    public static final float DEFAULT_SPEED = 0.07f;
    public static final float DEFAULT_SPAWN_RATE = 0.03f;
    public static final float DEFAULT_INCREASE_RATE = 1.2f;
    public static final boolean DEFAULT_COLLISION = true;
    public static final float DEFAULT_DECO_SPAWN_RATE = 0.1f;
    public static final long DEFAULT_ROUND_LENGTH_MS = 10000;
    public static float speed;

    /**
     * Spawnrate is for all three roads together. So 3 means 100% spawn rate.
     * To make the game not impossible to play, when 3 cars would spawn and the player could not pass,
     * the middle one will always not spawn.
     */
    public static float spawnRate;
    public static float increaseRate;
    public static boolean doCollision;

    private static int carsPassed;
    public static float decoSpawnRate;
    public static boolean dead;

    public static boolean endGame;

    private static long timeStampLastUpdate;
    public static int highScore;

    private static Properties initialProperties;

    public static void init() {
        speed = DEFAULT_SPEED;
        spawnRate = DEFAULT_SPAWN_RATE;
        increaseRate = DEFAULT_INCREASE_RATE;
        doCollision = DEFAULT_COLLISION;
        carsPassed = -1;
        decoSpawnRate = DEFAULT_DECO_SPAWN_RATE;
        dead = false;
        timeStampLastUpdate = new Date().getTime();
        endGame = false;
        highScore = 0;
    }

    public static void initFromProperties() {
        init();
        try {
            initialProperties = FileUtil.getConfigFile();
            speed = Float.parseFloat(initialProperties.getProperty("stats.speed", String.valueOf(speed) ));
            spawnRate = Float.parseFloat(initialProperties.getProperty("stats.spawnRate", String.valueOf(spawnRate)));
            increaseRate = Float.parseFloat(initialProperties.getProperty("stats.increaseRate", String.valueOf(increaseRate)));
            doCollision = Boolean.parseBoolean(initialProperties.getProperty("stats.doCollision", String.valueOf(doCollision)));
            highScore = Integer.parseInt(initialProperties.getProperty("stats.highscore", "0"));
        } catch (FileNotFoundException e) {
            System.out.println("could not find properties file, loading default.");
        }
    }
    public static void update() {
        long distanceToLastUpdate = new Date().getTime() - timeStampLastUpdate;
        if(distanceToLastUpdate > DEFAULT_ROUND_LENGTH_MS) {
            speed = speed * (increaseRate);
            spawnRate = spawnRate * (increaseRate);
            timeStampLastUpdate = new Date().getTime();
        }

        if(carsPassed > highScore) {
            highScore = carsPassed;
        }
    }

    public static void saveHighscore() {
        if(doCollision) {
            initialProperties.put("stats.highscore", String.valueOf(highScore));
            FileUtil.saveProperties(initialProperties);
        } else {
            System.out.println("Did not save highscore because collision is turned of you cheaty little bitch!");
        }
    }

    public static int getCarsPassed() {
        return carsPassed == -1 ? 0 : carsPassed;
    }

    public static void addCarsPassed(int additionalCarsPassed) {
        carsPassed += additionalCarsPassed;
    }
}