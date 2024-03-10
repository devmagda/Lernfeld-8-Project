package edu.p.eight.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    public static final DrawInstructions DEFAULT_DRAW_INSTRUCTION = DrawInstructions.CENTER;
    private final BufferedImage texture;
    private final DrawInstructions instruction;
    private final String textureName;

    public Texture(BufferedImage texture, String textureName) {
        this(texture, DEFAULT_DRAW_INSTRUCTION, textureName);
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
        Texture copy = new Texture(Texture.mirrorImageHorizontal(this.texture), mirroredInstruction, this.textureName);
        return copy;
    }

    private static BufferedImage mirrorImageHorizontal(BufferedImage originalImage) {
        // Create a horizontally mirrored image
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-originalImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }


    public Texture(BufferedImage texture, DrawInstructions instruction, String textureName) {
        this.texture = texture;
        this.instruction = instruction;
        this.textureName = textureName;
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
}
