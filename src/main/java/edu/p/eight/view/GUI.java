package edu.p.eight.view;

import edu.p.eight.actions.MoveAction;
import edu.p.eight.actions.MoveLeftAction;
import edu.p.eight.actions.MoveRightAction;
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
        this.imageLabel = new JLabel();
        initFrame(name, width, height);
        mapInput();
    }

    private void initFrame(String name, int width, int height) {
        this.gameFrame = new JFrame(name);
        gameFrame.setSize(width, height);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(imageLabel);
    }

    private void mapInput() {
        MoveAction left = new MoveLeftAction();
        MoveAction right = new MoveRightAction();

        InputMap im = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put( right.keyStroke, right.key);
        im.put( left.keyStroke,  left.key);

        ActionMap am = gameFrame.getRootPane().getActionMap();
        am.put( right.key, right);
        am.put( left.key,  left);
    }

    public GUI(String name) {
        this(name, TextureManager.getBackgroundTexture().getWidth(), TextureManager.getBackgroundTexture().getHeight());
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