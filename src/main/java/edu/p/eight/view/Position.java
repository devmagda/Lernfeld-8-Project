package edu.p.eight.view;

import edu.p.eight.model.entity.Entity;

public class Position {
    private float x;
    private float y;
    private float scale;

    public Position(float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    public Position(float x, float y) {
        this(x, y, 1);
    }

    public Position() {
        this(0, 0);
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getScale() {
        return scale;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setScale(Float scale) {
        this.scale = scale;
    }

    public Position copy() {
        return new Position(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", scale=" + scale +
                '}';
    }
}
