package edu.p.eight.model;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.model.entity.StreetEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameStateTest {

    private Entity car;

    @Before
    public void setup() {

        TextureManager.initialize();

        Stats.init();

        GameState.init();

        // Setting player position to the left
        GameState.getPlayerCar().setDistance(0);

        // Setting up the car with extended overlapping distance
        this.car = new StreetEntity(null);
        this.car.setOverlappingDistance(3f);
    }

    @Test
    public void testCrash() {
        assertTrue("Car should have crashed", playerCrashed());
    }

    public boolean playerCrashed() {
        try {
            // Setting speed any other than here did not work ..
            Stats.speed = Lane.LENGTH;

            // Enforce Spawn
            GameState.getLane(Lane.Lanes.CENTER).spawnEntity(this.car);

            // Update lane to crash the cars
            GameState.update();

        } catch (PlayerCrashedException e) {
            return true;
        }
        return false;
    }
}