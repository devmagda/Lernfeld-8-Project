package edu.p.eight.model.entity;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.view.Position;
import edu.p.eight.view.Texture;

import java.util.function.Function;

public class Entity {
    public static final float DEFAULT_OVERLAPPING_DISTANCE = 0.1f;
    protected float distance;
    protected final Texture texture;
    protected boolean toBeRemoved = false;
    protected float   overlappingDistance;
    protected boolean hasCollision;

    public Entity(float distance, Texture texture, boolean hasCollision) {
        this(distance, texture, DEFAULT_OVERLAPPING_DISTANCE, hasCollision);
    }

    protected Entity(float distance, Texture texture, float overlappingDistance, boolean hasCollision) {
        this.distance = distance;
        this.texture = texture;
        this.overlappingDistance = overlappingDistance;
        this.hasCollision = hasCollision;
        this.toBeRemoved = false;
    }

    /**
     * Creates the position in screen space from that object
     * @param drawCalculations the function used to calculate the position according to the distance
     * @return Returns the calculated position in screen space
     */
    public Position getPosition(Function<Float, Position> drawCalculations) {
        return drawCalculations.apply(this.distance);
    }

    /**
     * Checks if the two objects are colliding. This is defined through the overlapping distance
     * @param a the first object
     * @param b the second object
     * @return if any object and its collision radius is overlapping
     */
    public static boolean overlaps(Entity a, Entity b) {
        float overlap = Math.abs(a.distance - b.distance);
        return overlap < a.overlappingDistance || overlap < b.overlappingDistance;
    }
    public void setOverlappingDistance(float overlappingDistance) {
        this.overlappingDistance = overlappingDistance;
    }

    @Override
    public String toString() {
        return "< " + this.distance + " >";
    }

    public float getDistance() {
        return distance;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean hasCollision() {
        return hasCollision;
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
        return this.toBeRemoved;
    }


    /**
     * Moves the moving object closer to the player position
     * @param distance the distance to move the car forward in "3d" space
     * @throws EndOfLaneException thrown, when a car reaches the end of the street (used for handling car removing)
     */
    public void forward(float distance) throws EndOfLaneException {
        this.distance -= distance;
        if(this.distance < 0) {
            throw new EndOfLaneException("Car has reached the end of the lane ..");
        }
    }

    public void backward(float distance) throws EndOfLaneException {
        this.distance += distance;
        if(this.distance < 0) {
            throw new EndOfLaneException("Car has reached the end of the lane ..");
        }
    }
}
