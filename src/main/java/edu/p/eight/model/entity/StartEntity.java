package edu.p.eight.model.entity;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.view.Texture;

public class StartEntity extends DecoEntity {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 50;

    public StartEntity() {
        super(TextureManager.getStartTexture());
    }

}
