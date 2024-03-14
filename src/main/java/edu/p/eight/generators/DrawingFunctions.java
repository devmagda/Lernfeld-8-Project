package edu.p.eight.generators;

import edu.p.eight.model.Lane;
import edu.p.eight.view.Position;
import edu.p.eight.view.Texture;

public class DrawingFunctions {
    public static Position decoLeft(float distance) {
        Position start = Lane.DECO_LEFT_START;
        Position end = Lane.DECO_LEFT_END;
        return baseFunction(start, end, distance);
    }

    public static Position left(float distance) {
        Position start = Lane.LEFT_START;
        Position end = Lane.LEFT_END;
        return baseFunction(start, end, distance);
    }

    public static Position center(float distance) {
        Position start = Lane.CENTER_START;
        Position end = Lane.CENTER_END;
        return baseFunction(start, end, distance);
    }

    public static Position right(float distance) {
        Position start = Lane.RIGHT_START;
        Position end = Lane.RIGHT_END;
        return baseFunction(start, end, distance);
    }

    public static Position decoRight(float distance) {
        Position start = Lane.DECO_RIGHT_START;
        Position end = Lane.DECO_RIGHT_END;
        return baseFunction(start, end, distance);
    }

    private static Position baseFunction(Position start, Position end, float distance) {

        // Get current factor from distance
        float factor = getFactor(distance);

        // Gets total distance the car can travel from start to end
        float xDistance = start.getX() - end.getX();
        float yDistance = start.getY() - end.getY();

        // Gets distance it has actually traveled
        float x = start.getX() - factor * xDistance;
        float y = start.getY() - factor * yDistance;

        float textureScale = Texture.DEFAULT_TEXTURE_SIZE * factor;
        float halfTextureScale = textureScale / 2;

        // Shifted the object to be drawn on lower center instead
        x = x - halfTextureScale;
        y = y - textureScale;
        return new Position(x, y, factor);
    }

    private static float getFactor(float distance) {
        float factor = (Lane.LENGTH-distance)/Lane.LENGTH;
        return factor * factor * factor * factor + 0.0001f;
        // return 1 + 1/3* distance;
    }
}
