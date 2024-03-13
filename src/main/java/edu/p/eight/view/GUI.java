package edu.p.eight.view;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GUI {
    private JFrame gameFrame;

    public GUI(String name, int width, int height) {
        this.gameFrame = new JFrame(name);
        gameFrame.setSize(width, height);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        gameFrame.setVisible(true);
    }

    public GUI(String name) {
        this(name, Texture.DEFAULT_BACKGROUND_WIDTH, Texture.DEFAULT_BACKGROUND_HEIGHT);
    }

    public GUI() {
        this("90's Racing Game");
    }

    public void update() {
        // Write code to set the panel to the gui

        Texture texture = TextureManager.createView();

        // setImage ...
    }

    private void setImage(Texture texture) {
        BufferedImage image = texture.getTexture();

        // Do something with image to set the current frame via the Texture
    }
}
