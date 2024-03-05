package edu.p.eight.model.entity;

import edu.p.eight.view.Texture;

import static edu.p.eight.model.Lane.*;

public class PlayerEntity extends Entity {
    private Lanes lane;

    public PlayerEntity(Texture texture, Lanes lane) {
        super(0f, texture);
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
}
