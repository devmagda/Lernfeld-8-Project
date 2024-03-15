package edu.p.eight.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MusicUtil {
    private static Clip clip;
    public static List<File> getSongs() {
        return FileUtil.getFilesInDirectory(FileUtil.getBasePath() + "music/");
    }

    public static void playRandom() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        List<File> songs = getSongs();
        File randomSong = songs.get(new Random().nextInt(songs.size()));
        clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream( randomSong );
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stop() {
        if(clip != null) {
            clip.stop();
        }
    }
}
