package edu.p.eight.model;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.exceptions.SpawningFailedException;
import edu.p.eight.generators.DrawingFunctions;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.entity.DecoEntity;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.model.entity.PlayerEntity;
import edu.p.eight.model.entity.StreetEntity;
import edu.p.eight.view.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static edu.p.eight.model.Lane.*;

public class GameState {
    public static final int LANE_COUNT = 3;

    private static Map<Lanes, Lane> lanes;
    private static PlayerEntity playerCar;

    public static void init() {
        Stats.initFromProperties();
        playerCar = PlayerEntity.getRandom();
        initLanes();
    }

    private static void initLanes() {
        lanes = new HashMap<>();
        lanes.put( Lanes.LEFT,       new Lane( StreetEntity::getRandom, DrawingFunctions::left      ));
        lanes.put( Lanes.RIGHT,      new Lane( StreetEntity::getRandom, DrawingFunctions::right     ));
        lanes.put( Lanes.CENTER,     new Lane( StreetEntity::getRandom, DrawingFunctions::center,   StreetEntity.getStartEntity()));
        lanes.put( Lanes.DECO_LEFT,  new Lane( DecoEntity::getRandom,   DrawingFunctions::decoLeft  ));
        lanes.put( Lanes.DECO_RIGHT, new Lane( DecoEntity::getRandom,   DrawingFunctions::decoRight ));
    }

    public static void update() throws PlayerCrashedException {
        checkCollisions();
        updateLanes();
        checkCollisions();
    }

    public static void updateLanes() {
        int spawnCounter = 1;
        for(Lanes lane : getStreetLanes()) {
            int removedEntities = lanes.get(lane).update(Stats.speed);
            Stats.addCarsPassed(removedEntities);
            if(spawnCounter < LANE_COUNT) {
                try {
                    Entity entity = lanes.get(lane).trySpawnCar(Stats.spawnRate);
                    lanes.get(lane).spawnEntity(entity);
                    spawnCounter++;
                } catch (SpawningFailedException e) {
                    System.out.println("Spawning car failed .. but its okay");
                }
            }
        }
        for(Lanes lane : getDecoLanes()) {
            try {
                lanes.get(lane).update(Stats.speed * 2);
                Entity entity = lanes.get(lane).trySpawnCar(Stats.spawnRate);
                lanes.get(lane).spawnEntity(entity);
            } catch (SpawningFailedException e) {
                System.out.println("Spawning deco failed .. but its okay");
            }
        }
    }

    public static void checkCollisions() throws PlayerCrashedException {
        if(Lane.collides(lanes.get(playerCar.getLane()), playerCar) && Stats.doCollision) {
            throw new PlayerCrashedException();
        }
    }

    public static Lane getLane(Lanes lane) {
        return lanes.get(lane);
    }

    public static PlayerEntity getPlayerCar() {
        return playerCar;
    }

    public static Map<Entity, Function<Entity, Position>> getDrawingMap() {
        Map<Entity, Function<Entity, Position>> drawingMap = new HashMap();
        lanes.get( Lanes.DECO_LEFT  ).getEntities().stream().forEach(e -> drawingMap.put(e, lanes.get(Lanes.DECO_LEFT).getDrawCalculations()));
        lanes.get( Lanes.LEFT       ).getEntities().stream().forEach(e -> drawingMap.put(e, lanes.get(Lanes.LEFT).getDrawCalculations()));
        lanes.get( Lanes.CENTER     ).getEntities().stream().forEach(e -> drawingMap.put(e, lanes.get(Lanes.CENTER).getDrawCalculations()));
        lanes.get( Lanes.RIGHT      ).getEntities().stream().forEach(e -> drawingMap.put(e, lanes.get(Lanes.RIGHT).getDrawCalculations()));
        lanes.get( Lanes.DECO_RIGHT ).getEntities().stream().forEach(e -> drawingMap.put(e, lanes.get(Lanes.DECO_RIGHT).getDrawCalculations()));

        drawingMap.put(playerCar, lanes.get(playerCar.getLane()).getDrawCalculations());

        return drawingMap;
    }
}
