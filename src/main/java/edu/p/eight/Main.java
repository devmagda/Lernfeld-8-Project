package edu.p.eight;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;

class Main {
    public static void main(String[] args) throws PlayerCrashedException {


        GameState gameState = new GameState();

        for(int i = 0; i < 0; i++) {
            gameState.update(Lane.LaneSwitchAction.NONE);
            System.out.println(gameState);
        }


        TextureManager.initialize();
    }
}