package edu.p.eight.model;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.exceptions.SpawningFailedException;
import edu.p.eight.generators.DrawingFunctions;
import edu.p.eight.model.entity.*;

import java.util.*;

import static edu.p.eight.model.Lane.*;

public class GameState {
    public static final int LANE_COUNT = 3;

    private static Map<Lanes, Lane> lanes;
    private static PlayerEntity playerCar;

    public static void init() {
        Stats.init();
        playerCar = new PlayerEntity(null, Lanes.CENTER);
        initLanes();
    }

    private static void initLanes() {
        lanes = new HashMap<>();
        lanes.put( Lanes.LEFT,       new Lane( StreetEntity::getRandom, DrawingFunctions::left));
        lanes.put( Lanes.RIGHT,      new Lane( StreetEntity::getRandom, DrawingFunctions::right));
        lanes.put( Lanes.CENTER,     new Lane( StreetEntity::getRandom, DrawingFunctions::center));
        lanes.put( Lanes.DECO_LEFT,  new Lane( DecoEntity::getRandom,   DrawingFunctions::decoLeft));
        lanes.put( Lanes.DECO_RIGHT, new Lane( DecoEntity::getRandom,   DrawingFunctions::decoRight));
    }

    public static void update() throws PlayerCrashedException {
        update(LaneSwitchAction.NONE);
    }

    public static void update(LaneSwitchAction playerAction) throws PlayerCrashedException {
        checkCollisions();
        playerCar.move(playerAction);
        updateLanes();
    }

    public static void updateLanes() {
        int spawnCounter = 1;
        for(Lanes lane : getStreetLanes()) {
            lanes.get(lane).update(Stats.speed);
            if(spawnCounter < LANE_COUNT) {
                try {
                    MovingEntity entity = lanes.get(lane).trySpawnCar(Stats.spawnRate);
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
                lanes.get(lane).update(Stats.speed);
                MovingEntity entity = lanes.get(lane).trySpawnCar(Stats.spawnRate);
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

    public static void printLanes() {
        System.out.println(lanes.get(Lanes.DECO_LEFT));
        System.out.println(lanes.get(Lanes.LEFT));
        System.out.println(lanes.get(Lanes.CENTER));
        System.out.println(lanes.get(Lanes.RIGHT));
        System.out.println(lanes.get(Lanes.DECO_RIGHT));

    }
}
