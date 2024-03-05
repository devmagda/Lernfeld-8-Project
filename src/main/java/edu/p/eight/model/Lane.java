package edu.p.eight.model;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.exceptions.SpawningFailedException;
import edu.p.eight.model.entity.DecoEntity;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.model.entity.MovingEntity;
import edu.p.eight.model.entity.StreetEntity;
import edu.p.eight.view.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Lane<E extends MovingEntity> {
    public static final float LENGTH = 100F;
    private List<MovingEntity> entities;
    private final Function<Float, Position> drawCalculations;
    private final Supplier<E> generator;

    Lane(Supplier<E> generator, Function<Float, Position> drawCalculations) {
        if(drawCalculations == null || generator == null) {
            throw new RuntimeException("drawCalculations or generator cannot be null");
        }
        this.drawCalculations = drawCalculations;
        this.generator = generator;
        this.entities = new ArrayList<>();
    }

    public E update(float distance, float chance) throws SpawningFailedException {
        updateCars(distance);
        removeDeadEntities();
        return trySpawnCar(chance);
    }

    public void spawnEntity(MovingEntity car) {
            entities.add(car);
    }

    public E trySpawnCar(float chance) throws SpawningFailedException {
        if(doSpawnEntity(chance)) {
            E car = generator.get();
            if(hasSpace(car)) {
                return car;
            }
            throw new SpawningFailedException("Not enought space to spawn ..");
        }
        throw new SpawningFailedException("Chance was too low to spawn ..");
    }

    private boolean hasSpace(Entity other) {
        boolean value = entities.stream().filter(car -> Entity.overlaps(car, other)).count() == 0;
        return value;
    }

    private boolean doSpawnEntity(float chance) {
        E testObject = generator.get();
        float random = (float) Math.random();
        if (testObject instanceof DecoEntity) {
            float actualChance = DecoEntity.DEFAULT_SPAWN_RATE;
            return actualChance > random;
        }
        if(testObject instanceof StreetEntity) {
            float actualChance = chance * (1f / GameState.LANE_COUNT);
            return actualChance > random;
        }
        throw new IllegalStateException("Unsupported entity type: " + testObject.getClass().getName());
    }

    public void updateCars(float distance) {
        for(MovingEntity entity : entities) {
            try {
                entity.forward(distance);
            } catch (EndOfLaneException e) {
                entity.markDead();
            }
        }
    }

    public static boolean collides(Lane lane, Entity entity) {
        return !lane.hasSpace(entity);
    }

    public void removeDeadEntities() {
        this.entities = entities.stream().filter(entity -> !entity.isDead()).collect(Collectors.toList());
    }

    private String lineString() {
        List<String> represented = new ArrayList<>();
        for(int i = 0; i < LENGTH; i++) {
            represented.add("-");
        }

        for(Entity entity : entities) {
            int index = (int)entity.getDistance();
            if(index >= 0 && index < represented.size()) {
                represented.set(index, "#");
            }
        }
        return represented.stream().collect(Collectors.joining());
    }

    public static List<Lanes> getStreetLanes() {
        List<Lanes> lanes = new ArrayList<>();

        // Order is important to create Two cars left and right but not spawn a third one
        lanes.add(Lanes.LEFT);
        lanes.add(Lanes.RIGHT);
        lanes.add(Lanes.CENTER);
        return lanes;
    }

    public static List<Lanes> getDecoLanes() {
        List<Lanes> lanes = new ArrayList<>();
        lanes.add(Lanes.DECO_LEFT);
        lanes.add(Lanes.DECO_RIGHT);
        return lanes;
    }

    @Override
    public String toString() {
        return lineString();
    }

    public enum Lanes {
        DECO_LEFT,
        LEFT,
        RIGHT,
        CENTER,
        DECO_RIGHT
    }

    public enum LaneSwitchAction {
        LEFT,
        RIGHT,
        NONE
    }
}
