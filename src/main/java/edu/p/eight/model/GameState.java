package edu.p.eight.model;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.exceptions.SpawningFailedException;
import edu.p.eight.model.entity.*;
import edu.p.eight.view.Position;

import java.util.*;

import static edu.p.eight.model.Lane.*;

public class GameState {
    public static final int LANE_COUNT = 3;

    private Map<Lanes, Lane<? extends MovingEntity>> lanes;
    private PlayerEntity playerCar;

    private Stats stats;

    public GameState() {
        this.playerCar = new PlayerEntity(null, Lanes.CENTER);
        initLanes();
        this.stats = new Stats();

    }

    private Set<Lanes> getLanes() {
        return lanes.keySet();
    }

    private void initLanes() {
        this.lanes = new HashMap<>();
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

    public void update() throws PlayerCrashedException {
        update(LaneSwitchAction.NONE);
    }

    public void update(LaneSwitchAction playerAction) throws PlayerCrashedException {
        checkCollisions();
        this.playerCar.move(playerAction);
        updateLanes();
    }

    public void updateLanes() {
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

    public void checkCollisions() throws PlayerCrashedException {
        if(Lane.collides(this.lanes.get(this.playerCar.getLane()), this.playerCar)) {
            throw new PlayerCrashedException();
        }
    }

    @Override
    public String toString() {
        String border = "";
        for(int i = 0; i < LENGTH; i++) {
            border += "|";
        }

        String result = border + "\n";
        result += lanes.get(Lanes.DECO_RIGHT).toString() + "\n";
        result += lanes.get(Lanes.RIGHT).toString() + "\n";
        result += lanes.get(Lanes.CENTER).toString() + "\n";
        result += lanes.get(Lanes.LEFT).toString() + "\n";
        result += lanes.get(Lanes.DECO_LEFT).toString() + "\n";
        result += border;
        return result;
    }
}
