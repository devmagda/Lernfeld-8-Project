package edu.p.eight.view;

import edu.p.eight.actions.*;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.utils.TextureUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


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
        MoveAction forward = new MoveForwardAction();
        MoveAction backward = new MoveBackwardAction();

        InputMap im = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(right.keyStroke, right.key);
        im.put(left.keyStroke, left.key);
        im.put(forward.keyStroke, forward.key);
        im.put(backward.keyStroke, backward.key);

        ActionMap am = gameFrame.getRootPane().getActionMap();
        am.put(right.key, right);
        am.put(left.key, left);
        am.put(forward.key, forward);
        am.put(backward.key, backward);
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
        setImage(TextureUtil.createView());
    }

    private void setImage(Texture texture) {
        BufferedImage image = texture.getTexture();
        Image scaledImage = image.getScaledInstance(gameFrame.getWidth(), gameFrame.getHeight(), Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }
}