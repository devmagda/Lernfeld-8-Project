package edu.p.eight.model.entity;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.model.Lane;
import edu.p.eight.view.Texture;

public class MovingEntity extends Entity {

    protected MovingEntity(Texture texture, boolean hasCollision) {
        super(Lane.LENGTH, texture, hasCollision);
    }
}
