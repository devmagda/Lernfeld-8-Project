package edu.p.eight.model.entity;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.model.Lane;
import edu.p.eight.view.Texture;

public abstract class MovingEntity extends Entity {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private boolean toBeRemoved = false;

    protected MovingEntity(Texture texture) {
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
}
