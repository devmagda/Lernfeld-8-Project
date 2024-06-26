package edu.p.eight.model.entity;

import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.Lane;
import edu.p.eight.view.Texture;

import java.util.Random;

import static edu.p.eight.model.Lane.*;

public class PlayerEntity extends Entity {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 50;

    public static final Texture.DrawInstructions DEFAULT_DRAW_INSTRUCTIONS = Texture.DrawInstructions.CENTER;


    private Lanes lane;

    public PlayerEntity(Texture texture, Lanes lane) {
        super(LENGTH * 0.2f, texture, true);
        this.lane = lane;
    }

    /**
     * Returns the lane the playerCar is currently driving in
     * @return returns the lane the playerCar is
     */
    public Lanes getLane() {
        return lane;
    }

    /**
     * Moves the playerCar in the given direction (Left, Right)
     * @param action
     */
    public void move(LaneSwitchAction action) {
        switch(action) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }

    /**
     * The simple function to move to the left lane.
     * If we are on the left lane, do nothing
     */
    public void moveLeft() {
        switch (this.lane) {
            case LEFT, CENTER -> this.lane = Lanes.LEFT;
            case RIGHT -> this.lane = Lanes.CENTER;
        }
    }


    /**
     * The simple function to move to the right lane.
     * If we are on the right lane, do nothing
     */
    public void moveRight() {
        switch (this.lane) {
            case RIGHT , CENTER -> this.lane = Lanes.RIGHT;
            case LEFT -> this.lane = Lanes.CENTER;
        }
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public static PlayerEntity getRandom() {
        int index = new Random().nextInt(TextureManager.getPlayerTextures().size());
        Texture deco = TextureManager.getPlayerTextures().get(index);
        return new PlayerEntity(deco, Lanes.CENTER);
    }
}
