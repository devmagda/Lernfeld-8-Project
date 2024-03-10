package edu.p.eight.manager;

import edu.p.eight.utils.FileUtil;
import edu.p.eight.view.Texture;

import java.util.List;
import java.util.stream.Collectors;

public class TextureManager {


    public static final String DEFAULT_START_TEXTURE_NAME = "TemplateStart.png";
    public static final String DEFAULT_BACKGROUND_TEXTURE_NAME = "TemplateBackground.png";
    private static Texture backgroundTexture;

    private static List<Texture> carTextures;
    private static List<Texture> decoTextures;
    private static List<Texture> playerTextures;
    private static Texture startTexture;

    private static List<String> songs;

    /**
     * The AssetManager holds all the textures in memory for faster loading
     * @throws RuntimeException When an error on file loading is happening
     */
    public static void initialize() throws RuntimeException {
        carTextures = loadCarTextures();
        decoTextures = loadStaticTextures();
        playerTextures = loadPlayerTextures();
        startTexture = loadStartTexture();
        backgroundTexture = loadBackgroundTexture();
        songs = getSongs();
    }

    private static List<Texture> loadCarTextures() {
        return FileUtil.getFilesInDirectory("entities/moving/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadStaticTextures() {
        return FileUtil.getFilesInDirectory("entities/static/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadPlayerTextures() {
        return FileUtil.getFilesInDirectory("entities/player/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadSpecialTextures() {
        return FileUtil.getFilesInDirectory("entities/special/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    public static Texture loadStartTexture() {
        for(Texture texture : loadSpecialTextures()) {
            if(DEFAULT_START_TEXTURE_NAME.equals(texture.getTextureName())) {
                return texture;
            }
        }
        throw new RuntimeException("Could not find start texture");
    }

    private static Texture loadBackgroundTexture() {
        for(Texture texture : loadSpecialTextures()) {
            if(DEFAULT_BACKGROUND_TEXTURE_NAME.equals(texture.getTextureName())) {
                return texture;
            }
        }
        throw new RuntimeException("Could not find start texture");
    }

    public static List<String> getSongs() {
        return songs;
    }


    public static List<Texture> getCarTextures() {
        return carTextures;
    }

    public static List<Texture> getDecoTextures() {
        return decoTextures;
    }

    public static List<Texture> getPlayerTextures() {
        return playerTextures;
    }

    public static Texture getStartTexture() {
        return startTexture;
    }

    public static Texture getBackgroundTexture() {
        return backgroundTexture;
    }
}
