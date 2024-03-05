package edu.p.eight.model;

import edu.p.eight.view.Texture;

public class DecoEntity extends MovingEntity{
    public static final float DEFAULT_SPAWN_RATE = 0.5f;
    public DecoEntity(Texture texture) {
        super(texture);
    }

    /**
     * TODO: Implement
     * create a function to create a random car object
     * @return the random car object
     */
    public static DecoEntity getRandom() {
        return new DecoEntity(null);
    }
}
