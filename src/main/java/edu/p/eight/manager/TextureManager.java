package edu.p.eight.manager;

import edu.p.eight.builder.TextureBuilder;
import edu.p.eight.model.GameState;
import edu.p.eight.model.entity.DecoEntity;
import edu.p.eight.model.entity.MovingEntity;
import edu.p.eight.model.entity.PlayerEntity;
import edu.p.eight.model.entity.StartEntity;
import edu.p.eight.utils.FileUtil;
import edu.p.eight.view.Texture;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TextureManager {
    private static boolean initialized;
    public static final String DEFAULT_START_TEXTURE_NAME = "TemplateStart.png";
    public static final String DEFAULT_BACKGROUND_TEXTURE_NAME = "TemplateBackground.png";

    private static Texture startTexture;
    private static Texture backgroundTexture;
    private static List<Texture> carTextures;
    private static List<Texture> decoTextures;
    private static List<Texture> playerTextures;


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
        initialized = true;
    }

    private static List<Texture> loadCarTextures() {
        return FileUtil.getFilesInDirectory(FileUtil.getBasePath()  + "entities/moving/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadStaticTextures() {
        return FileUtil.getFilesInDirectory(FileUtil.getBasePath()  + "entities/static/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadPlayerTextures() {
        return FileUtil.getFilesInDirectory(FileUtil.getBasePath()  + "entities/player/").stream().map(Texture::fromFile).collect(Collectors.toList());
    }

    private static List<Texture> loadSpecialTextures() {
        return FileUtil.getFilesInDirectory(FileUtil.getBasePath()  + "entities/special/").stream().map(Texture::fromFile).collect(Collectors.toList());
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

    public static List<Texture> getCarTextures() {
        TextureManager.isInitialized();
        return carTextures;
    }

    public static List<Texture> getDecoTextures() {
        TextureManager.isInitialized();
        return decoTextures;
    }

    public static List<Texture> getPlayerTextures() {
        TextureManager.isInitialized();
        return playerTextures;
    }

    public static Texture getStartTexture() {
        TextureManager.isInitialized();
        return startTexture;
    }

    public static Texture getBackgroundTexture() {
        TextureManager.isInitialized();
        return backgroundTexture;
    }

    private static void isInitialized() throws RuntimeException {
        if(!initialized)
            throw new RuntimeException("TextureManager is not initialized! This needs to be done first before trying to load textures ..");
    }

    public static Texture createView() {
        int index = new Random().nextInt(TextureManager.getCarTextures().size());
        Texture car = TextureManager.getCarTextures().get(index);
        int x = new Random().nextInt(300);
        int y = new Random().nextInt(300);
        Texture texture = new TextureBuilder(TextureManager.getBackgroundTexture()).add(car, x, y, 1f).build();
        return texture;
    }
}
