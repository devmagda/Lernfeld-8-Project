package edu.p.eight;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;
import edu.p.eight.utils.FileUtil;
import edu.p.eight.view.GUI;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Main {
    public static void main(String[] args) throws PlayerCrashedException, IOException {

        // Has to be called first
        TextureManager.initialize();

        GameState gameState = new GameState();

        GUI gui = new GUI();

        for(int i = 0; i < 0; i++) {
            gameState.update(Lane.LaneSwitchAction.NONE);
            System.out.println(gameState);
            gui.update(gameState);
        }


    }
}