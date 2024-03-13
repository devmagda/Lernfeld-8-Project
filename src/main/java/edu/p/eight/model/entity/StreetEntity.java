package edu.p.eight.model.entity;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.view.Texture;

import java.util.Random;

public class StreetEntity extends MovingEntity {

    public static final Texture.DrawInstructions DEFAULT_DRAW_INSTRUCTIONS = Texture.DrawInstructions.CENTER;
    public StreetEntity(Texture texture) {
        super(texture);
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
}
