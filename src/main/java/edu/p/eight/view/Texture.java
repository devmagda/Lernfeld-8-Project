package edu.p.eight.view;

import java.awt.*;

public class Texture {
    private Graphics2D texture;
    private DrawInstructions instruction;

    public Texture(Graphics2D texture, DrawInstructions instruction) {
        this.texture = texture;
        this.instruction = instruction;
    }

    public Graphics2D scaled(float scale) {
        return null;
    }

    public enum DrawInstructions {
        CENTER,
        UPPER_LEFT,
        UPPER_RIGHT,
        LOWER_LEFT,
        LOWER_RIGHT
    }
}
