package edu.p.eight;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;
import edu.p.eight.model.entity.StreetEntity;

class Main {
    public static void main(String[] args) throws PlayerCrashedException {
        GameState gameState = new GameState();

        for(int i = 0; i < 100; i++) {
            gameState.update(Lane.LaneSwitchAction.NONE);
            System.out.println(gameState);
        }


        System.out.println(StringUtils.add("Hello, ", null));

    }
}