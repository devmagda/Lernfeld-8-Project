package edu.p.eight;

import edu.p.eight.exceptions.PlayerCrashedException;
import edu.p.eight.manager.TextureManager;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Lane;
import edu.p.eight.model.Stats;
import edu.p.eight.utils.MusicUtil;
import edu.p.eight.view.GUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Has to be called first
        TextureManager.initialize();

        GameState.init();

        GUI gui = new GUI();
        gui.show();

        //MusicUtil.playRandom();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    GameState.update();
                    Stats.update();

                    // GameState.printLanes();
                } catch (PlayerCrashedException e) {
                    Stats.dead = true;
                    MusicUtil.stop();
                }
                gui.update();
                if(Stats.dead) {
                    this.cancel();
                }

            }
        };
        // Start the Timer with a delay of 0
        // and repeat every 500 milliseconds.
        timer.schedule(task, 0, 60);
    }
}