package edu.p.eight.model.entity;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.view.Texture;

import java.util.Random;

public class DecoEntity extends MovingEntity {
    
    public DecoEntity(Texture texture) {
        super(texture, false);
    }

    /**
     * TODO: Implement
     * create a function to create a random car object
     * @return the random car object
     */
    public static DecoEntity getRandom() {
        int index = new Random().nextInt(TextureManager.getDecoTextures().size());
        Texture deco = TextureManager.getDecoTextures().get(index);
        return new DecoEntity(deco);
    }
}
