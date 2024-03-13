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
    public static void main(String[] args) throws PlayerCrashedException {

        // Has to be called first
        TextureManager.initialize();

        GameState.init();

        GUI gui = new GUI();
        gui.show();

        for(int i = 0; i < 0; i++) {
            GameState.update(Lane.LaneSwitchAction.NONE);
            gui.update();
            gui.show();
        }


    }
}