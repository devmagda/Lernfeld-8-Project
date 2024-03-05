package edu.p.eight.model;

import edu.p.eight.view.Texture;

public class StreetEntity extends MovingEntity {
    public StreetEntity(Texture texture) {
        super(texture);
    }

    /**
     * TODO: Implement
     * create a function to create a random car object
     * @return the random car object
     */
    public static StreetEntity getRandom() {
        return new StreetEntity(null);
    }
}
