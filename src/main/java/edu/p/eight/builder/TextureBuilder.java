package edu.p.eight.builder;

import edu.p.eight.view.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextureBuilder {
    private int width;
    private int height;
    private Graphics2D graphics;
    private BufferedImage result;
    private String name;
    private Texture.DrawInstructions drawInstructions;

    public TextureBuilder(Texture texture) {
        this(texture.getTextureName(), texture.getInstruction(), texture.getWidth(), texture.getHeight());
        add(texture, 0, 0, 1f);
    }

    public TextureBuilder(String name, Texture.DrawInstructions drawInstructions, int width, int height ) {
        this.result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = result.createGraphics();
        this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.name = name;
        this.drawInstructions = drawInstructions;
        this.width = width;
        this.height = height;
    }

    public TextureBuilder add(Texture texture, int x, int y, float scale) {
        this.graphics.drawImage(texture.getTexture(), x, y, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), null);
        return this;
    }

    public Texture build() {
        this.graphics.dispose();
        return new Texture(this.result, Texture.DrawInstructions.UPPER_LEFT, this.name, this.width, this.height);
    }
}
