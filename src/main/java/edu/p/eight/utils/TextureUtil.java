package edu.p.eight.utils;

import edu.p.eight.builder.TextureBuilder;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;
import edu.p.eight.model.Stats;
import edu.p.eight.model.entity.Entity;
import edu.p.eight.model.entity.PlayerEntity;
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



//        drawLanes(builder);
//        drawCar(builder, GameState.getPlayerCar());
//        drawScore(builder);
        drawStuff(builder);
        return builder.build();
    }

    private static void drawScore(TextureBuilder builder) {
        int actualScore = Stats.carsPassed == -1 ? 0 : Stats.carsPassed;
        String text = "Cars passed: " + actualScore;
        builder.addText(text, 100, 100);
    }

    private static void drawLanes(TextureBuilder builder) {
        drawLane(builder, GameState.getLane(Lane.Lanes.DECO_LEFT));
        drawLane(builder, GameState.getLane(Lane.Lanes.DECO_RIGHT));
        drawLane(builder, GameState.getLane(Lane.Lanes.LEFT));
        drawLane(builder, GameState.getLane(Lane.Lanes.CENTER));
        drawLane(builder, GameState.getLane(Lane.Lanes.RIGHT));

    }

    private static void drawCar(TextureBuilder builder, PlayerEntity playerCar) {
        int y = 350;
        switch (playerCar.getLane()) {
            case LEFT ->   builder.add(playerCar.getTexture(), 50,  y, 1);
            case CENTER -> builder.add(playerCar.getTexture(), 200, y, 1);
            case RIGHT ->  builder.add(playerCar.getTexture(), 350, y, 1);
        }
    }

    private static void drawLane(TextureBuilder builder, Lane lane) {
        for(Entity entity : lane.getEntities()) {
            Position pos = lane.getDrawCalculations().apply(entity);
            builder.add(entity.getTexture(), pos.getX().intValue(), pos.getY().intValue(), pos.getScale());
        }
    }

    private static void drawStuff(TextureBuilder builder) {
        Map<Entity, Function<Entity, Position>> map = GameState.getDrawingMap();
        List<Entity> list = map.keySet().stream().sorted(Comparator.comparingDouble(Entity::getDistance)).collect(Collectors.toList());
        Collections.reverse(list);
        for(Entity entity : list) {
            Function<Entity, Position> drawInstruction = map.get(entity);
            builder.add(entity, drawInstruction);
        }
    }
}
