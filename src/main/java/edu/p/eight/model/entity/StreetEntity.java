package edu.p.eight.model.entity;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.Lane;
import edu.p.eight.view.Texture;

import java.util.Random;

public class StreetEntity extends MovingEntity {

    public static final float DEFAULT_START = Lane.LENGTH / 2;
    public StreetEntity(Texture texture) {
        super(texture, true);
    }

    /**
     * TODO: Implement
     * create a function to create a random car object
     * @return the random car object
     */
    public static StreetEntity getRandom() {
        int index = new Random().nextInt(TextureManager.getCarTextures().size());
        Texture car = TextureManager.getCarTextures().get(index);

        return new StreetEntity(car);
    }

    public static StreetEntity getStartEntity() {
        StreetEntity entity = new StreetEntity(TextureManager.getStartTexture());
        entity.disableCollision();
        entity.distance = DEFAULT_START;
        return entity;
    }

    public void disableCollision() {
        this.hasCollision = false;
    }
}
