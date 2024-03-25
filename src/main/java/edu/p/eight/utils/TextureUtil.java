package edu.p.eight.utils;

import edu.p.eight.builder.TextureBuilder;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Stats;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.view.Position;
import edu.p.eight.view.Texture;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextureUtil {

    public static Texture createView() {
        TextureBuilder builder = new TextureBuilder(TextureManager.getBackgroundTexture());
        drawGameState(builder);
        drawScore(builder);
        drawOverlay(builder);
        return builder.build();
    }

    private static void drawOverlay(TextureBuilder builder) {
        Texture texture = TextureManager.getOverlayTexture();
        builder.add(texture, 0, 0, 1);
    }

    private static void drawScore(TextureBuilder builder) {
        int actualScore = Stats.carsPassed == -1 ? 0 : Stats.carsPassed;
        String text = "Cars passed: " + actualScore;
        builder.addText(text, 100, 100);
    }

    private static void drawGameState(TextureBuilder builder) {
        Map<Entity, Function<Entity, Position>> map = GameState.getDrawingMap();
        List<Entity> list = map.keySet().stream().sorted(Comparator.comparingDouble(Entity::getDistance)).collect(Collectors.toList());
        Collections.reverse(list);
        for(Entity entity : list) {
            Function<Entity, Position> drawInstruction = map.get(entity);
            builder.add(entity, drawInstruction);
        }
    }
}
