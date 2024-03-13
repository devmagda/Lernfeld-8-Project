package edu.p.eight.model.entity;

import edu.p.eight.view.Position;
import edu.p.eight.view.Texture;

import java.util.function.Function;

public abstract class Entity {
    public static final float DEFAULT_OVERLAPPING_DISTANCE = 1f;
    protected float distance;
    protected final Texture texture;
    protected final float overlappingDistance;

    protected Entity(float distance, Texture texture) {
        this(distance, texture, DEFAULT_OVERLAPPING_DISTANCE);
    }

    protected Entity(float distance, Texture texture, float overlappingDistance) {
        this.distance = distance;
        this.texture = texture;
        this.overlappingDistance = overlappingDistance;
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


}
