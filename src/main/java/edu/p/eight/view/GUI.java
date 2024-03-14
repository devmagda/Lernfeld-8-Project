package edu.p.eight.view;

import edu.p.eight.builder.TextureBuilder;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;
import edu.p.eight.view.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        InputMap im = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gameFrame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), "left");
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.getPlayerCar().moveLeft();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "right");
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.getPlayerCar().moveRight();
            }
        });
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
        setImage(TextureManager.createView());
    }

    private void setImage(Texture texture) {
        BufferedImage image = texture.getTexture();
        Image scaledImage = image.getScaledInstance(gameFrame.getWidth(), gameFrame.getHeight(), Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }
}