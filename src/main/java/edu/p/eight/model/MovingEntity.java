package edu.p.eight.model;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.view.Texture;

public class MovingEntity extends Entity {

    private boolean toBeRemoved = false;

    public MovingEntity(Texture texture) {
        super(Lane.LENGTH, texture);
    }

    /**
     * Moves the moving object closer to the player position
     * @param distance the distance to move the car forward in "3d" space
     * @throws EndOfLaneException thrown, when a car reaches the end of the street (used for handling car removing)
     */
    public void forward(float distance) throws EndOfLaneException {
        this.distance -= distance;
        if(distance < 0) {
            throw new EndOfLaneException("Car has reached the end of the lane ..");
        }
    }

    /**
     * Marks a car as dead. It will be removed once we check for alive cars
     */
    public void markDead() {
        this.toBeRemoved = true;
    }

    /**
     * Checks, if a car is dead
     * @return true if the car is dead
     */
    public boolean isDead() {
        return toBeRemoved;
    }

    /**
     * TODO: Implement
     * create a function to create a random car object
     * @return the random car object
     */
    public static MovingEntity getRandom() {
        throw new RuntimeException("This object should not be initialized!");
    }
}
