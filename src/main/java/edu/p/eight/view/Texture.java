package edu.p.eight.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    public static final  DrawInstructions DEFAULT_DRAW_INSTRUCTION = DrawInstructions.CENTER;
    private final BufferedImage texture;
    private DrawInstructions instruction;
    private final String textureName;

    private final int width;
    private final int height;

    public Texture(BufferedImage texture, DrawInstructions instruction, String textureName, int width, int height) {
        this.texture = texture;
        this.instruction = instruction;
        this.textureName = textureName;
        this.width = width;
        this.height = height;
    }

    public Texture(BufferedImage texture, String textureName) {
        this(texture, DEFAULT_DRAW_INSTRUCTION, textureName, texture.getWidth(), texture.getHeight());
    }

    public Texture mirrored() {
        DrawInstructions mirroredInstruction = DEFAULT_DRAW_INSTRUCTION;
        switch(this.instruction) {
            case CENTER -> mirroredInstruction = DrawInstructions.CENTER;
            case UPPER_LEFT -> mirroredInstruction = DrawInstructions.UPPER_RIGHT;
            case UPPER_RIGHT -> mirroredInstruction = DrawInstructions.UPPER_LEFT;
            case LOWER_LEFT -> mirroredInstruction = DrawInstructions.LOWER_RIGHT;
            case LOWER_RIGHT -> mirroredInstruction = DrawInstructions.LOWER_LEFT;
            case LOWER_CENTER -> mirroredInstruction = DrawInstructions.LOWER_CENTER;
        }
        Texture copy = new Texture(Texture.mirrorImageHorizontal(this.texture), mirroredInstruction, this.textureName, this.width, this.height);
        return copy;
    }

    private static BufferedImage mirrorImageHorizontal(BufferedImage originalImage) {
        // Create a horizontally mirrored image
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-originalImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }

    public enum DrawInstructions {
        CENTER,
        UPPER_LEFT,
        UPPER_RIGHT,
        LOWER_LEFT,
        LOWER_RIGHT,
        LOWER_CENTER
    }

    public static Texture fromFile(File file) {
        if(file != null) {
            try {
                return new Texture(ImageIO.read(file), file.getName());
            } catch (IOException e) {
                throw new RuntimeException("Could not load texture from file <" + file.getName() + ">");
            }
        }
        throw new RuntimeException("File was null .. ");
    }

    public String getTextureName() {
        return this.textureName;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public DrawInstructions getInstruction() {
        return instruction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setInstruction(DrawInstructions instruction) {
        this.instruction = instruction;
    }
}
