package edu.p.eight.generators;

import edu.p.eight.model.Lane;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.model.entity.MovingEntity;
import edu.p.eight.view.Position;
import edu.p.eight.view.Texture;

public class DrawingFunctions {
    public static Position decoLeft(Entity entity) {
        Position start = Lane.DECO_LEFT_START;
        Position end = Lane.DECO_LEFT_END;
        return baseFunction(start, end, entity);
    }

    public static Position left(Entity entity) {
        Position start = Lane.LEFT_START;
        Position end = Lane.LEFT_END;
        return baseFunction(start, end, entity);
    }

    public static Position center(Entity entity) {
        Position start = Lane.CENTER_START;
        Position end = Lane.CENTER_END;
        return baseFunction(start, end, entity);
    }

    public static Position right(Entity entity) {
        Position start = Lane.RIGHT_START;
        Position end = Lane.RIGHT_END;
        return baseFunction(start, end, entity);
    }

    public static Position decoRight(Entity entity) {
        Position start = Lane.DECO_RIGHT_START;
        Position end = Lane.DECO_RIGHT_END;
        return baseFunction(start, end, entity);
    }

    private static Position baseFunction(Position start, Position end, Entity entity) {

        // Get current factor from distance
        float factor = getFactor(entity.getDistance());

        // Gets total distance the car can travel from start to end
        float xDistance = start.getX() - end.getX();
        float yDistance = start.getY() - end.getY();

        // Gets distance it has actually traveled
        float x = start.getX() - factor * xDistance;
        float y = start.getY() - factor * yDistance;

        float textureScaleX = entity.getTexture().getWidth() * factor;
        float textureScaleY = entity.getTexture().getHeight() * factor;

        // Shifted the object to be drawn on lower center instead
        x = x - textureScaleX / 2;
        y = y - textureScaleY;
        return new Position(x, y, factor);
    }

    private static float getFactor(float distance) {
        float factor = (Lane.LENGTH-distance)/Lane.LENGTH;
        return factor * factor * factor * factor; // x^4
        // return -1f * ((factor - 1f) * (factor - 1f) * (factor - 1f) * (factor - 1f)) + 1; // -(x-1)^4+1
        //return factor;
        // return 1 + 1/3* distance;
    }
}
