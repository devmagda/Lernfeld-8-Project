package edu.p.eight.model;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.exceptions.SpawningFailedException;
import edu.p.eight.model.entity.*;
import edu.p.eight.view.Position;

import java.util.*;

import static edu.p.eight.model.Lane.*;

public class GameState {
    public static final int LANE_COUNT = 3;

    private static Map<Lanes, Lane<? extends MovingEntity>> lanes;
    private static PlayerEntity playerCar;

    public static void init() {
        playerCar = new PlayerEntity(null, Lanes.CENTER);
        initLanes();
    }

    private static void initLanes() {
        lanes = new HashMap<>();
        lanes.put(Lanes.LEFT, new Lane<>(
                StreetEntity::getRandom,
                l -> {
            Position pos = new Position();


            return pos;
        }));
        lanes.put(Lanes.RIGHT, new Lane<>(
                StreetEntity::getRandom,
                l -> {
            Position pos = new Position();


            return pos;
        }));
        lanes.put(Lanes.CENTER, new Lane<>(
                StreetEntity::getRandom,
                l -> {
            Position pos = new Position();


            return pos;
        }));
        lanes.put(Lanes.DECO_LEFT, new Lane<>(
                DecoEntity::getRandom,
                l -> {
            Position pos = new Position();


            return pos;
        }));
        lanes.put(Lanes.DECO_RIGHT, new Lane<>(
                DecoEntity::getRandom,
                l -> {
            Position pos = new Position();


            return pos;
        }));
    }

    public static void update() throws PlayerCrashedException {
        update(LaneSwitchAction.NONE);
    }

    public static void update(LaneSwitchAction playerAction) throws PlayerCrashedException {
        checkCollisions();
        this.playerCar.move(playerAction);
        updateLanes();
    }

    public static void updateLanes() {
        int spawnCounter = 1;
        for(Lanes lane : getStreetLanes()) {
            if(spawnCounter < LANE_COUNT) {
                try {
                    MovingEntity entity = lanes.get(lane).update(stats.speed, stats.spawnRate);
                    lanes.get(lane).spawnEntity(entity);
                    spawnCounter++;
                } catch (SpawningFailedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("SpawnCount Failure");
            }
        }
        for(Lanes lane : getDecoLanes()) {
            try {
                MovingEntity entity = lanes.get(lane).update(stats.speed, stats.spawnRate);
                lanes.get(lane).spawnEntity(entity);
            } catch (SpawningFailedException e) {
                System.out.println("Spawning failed because of to many collisions");
            }
        }
    }

    public static void checkCollisions() throws PlayerCrashedException {
        if(Lane.collides(lanes.get(playerCar.getLane()), playerCar)) {
            throw new PlayerCrashedException();
        }
    }

    public static Lane getLane(Lanes lane) {
        return lanes.get(lane);
    }
}
