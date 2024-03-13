package edu.p.eight.generators;

import edu.p.eight.model.Lane;
import edu.p.eight.view.Position;

public class DrawingFunctions {
    public static Position decoLeft(float distance) {
        Position start = Lane.DECO_LEFT_START;
        Position end = Lane.DECO_LEFT_END;
        float factor = getFactor(distance);
        float xDistance = Math.abs(start.getX() - end.getX());
        float yDistance = Math.abs(start.getY() - end.getY());
        float x = start.getX() - factor * xDistance;
        float y = start.getY() + factor * yDistance;
        float scale = factor * 2;
        return new Position(x, y, scale);
    }

    public static Position left(float distance) {
        Position start = Lane.LEFT_START;
        Position end = Lane.LEFT_END;
        float factor = getFactor(distance);
        float xDistance = Math.abs(start.getX() - end.getX());
        float yDistance = Math.abs(start.getY() - end.getY());
        float x = start.getX() - factor * xDistance;
        float y = start.getY() + factor * yDistance;
        float scale = factor * 2;
        Position position = new Position(x, y, scale);
        System.out.println(position);
        return position;
    }

    public static Position center(float distance) {
        Position start = Lane.CENTER_START;
        Position end = Lane.CENTER_END;
        float factor = getFactor(distance);
        float xDistance = Math.abs(start.getX() - end.getX());
        float yDistance = Math.abs(start.getY() - end.getY());
        float x = start.getX() + factor * xDistance;
        float y = start.getY() + factor * yDistance;
        float scale = factor * 2;
        return new Position(x, y, scale);
    }

    public static Position right(float distance) {
        Position start = Lane.RIGHT_START;
        Position end = Lane.RIGHT_END;
        float factor = getFactor(distance);
        float xDistance = Math.abs(start.getX() - end.getX());
        float yDistance = Math.abs(start.getY() - end.getY());
        float x = start.getX() + factor * xDistance;
        float y = start.getY() + factor * yDistance;
        float scale = factor * 2;
        return new Position(x, y, scale);
    }

    public static Position decoRight(float distance) {
        Position start = Lane.DECO_RIGHT_START;
        Position end = Lane.DECO_RIGHT_END;
        float factor = getFactor(distance);
        float xDistance = Math.abs(start.getX() - end.getX());
        float yDistance = Math.abs(start.getY() - end.getY());
        float x = start.getX() + factor * xDistance;
        float y = start.getY() + factor * yDistance;
        float scale = factor * 2;
        return new Position(x, y, scale);
    }

    private static float getFactor(float distance) {
        float factor = (Lane.LENGTH-distance)/Lane.LENGTH;
        return factor * factor * factor * factor;
        // return 1 + 1/3* distance;
    }
}
