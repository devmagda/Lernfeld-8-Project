package edu.p.eight.view;

import edu.p.eight.builder.TextureBuilder;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.view.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;



public class GUI {
    private JFrame gameFrame;
    private JLabel imageLabel;

    public GUI(String name, int width, int height) {
        this.gameFrame = new JFrame(name);
        this.imageLabel = new JLabel();
        gameFrame.setSize(width, height);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(imageLabel);
    }

    public GUI(String name) {
        this(name, Texture.DEFAULT_BACKGROUND_WIDTH, Texture.DEFAULT_BACKGROUND_HEIGHT);
    }

    public GUI() {
        this("90's Racing Game");
    }

    public void show() {
        gameFrame.setVisible(true);
    }

    public void update() {
        Texture texture = TextureManager.createView();
/*      int index = new Random().nextInt(TextureManager.getCarTextures().size());
        Texture car = TextureManager.getCarTextures().get(index);
        int x = new Random().nextInt(300);
        int y = new Random().nextInt(300);
        Texture texture = new TextureBuilder(TextureManager.getBackgroundTexture()).add(car, x, y, 1f).build();*/
        setImage(texture);
    }

    private void setImage(Texture texture) {
        BufferedImage image = texture.getTexture();
        Image scaledImage = image.getScaledInstance(gameFrame.getWidth(), gameFrame.getHeight(), Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }
}