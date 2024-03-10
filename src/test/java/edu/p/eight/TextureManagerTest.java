// StringUtilsTest.java
package edu.p.eight;

import edu.p.eight.manager.TextureManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextureManagerTest {

    @Before
    public void setup() {
        TextureManager.initialize();
    }

    @Test
    public void testStartTexture() {
        assertEquals(TextureManager.DEFAULT_START_TEXTURE_NAME, TextureManager.getStartTexture().getTextureName());
    }


    @Test
    public void testBackgroundTexture() {
        assertEquals(TextureManager.DEFAULT_BACKGROUND_TEXTURE_NAME, TextureManager.getBackgroundTexture().getTextureName());
    }

    @Test
    public void testListsNotEmpty() {
        assertNotNull(TextureManager.getCarTextures());
        assertNotNull(TextureManager.getDecoTextures());
        assertNotNull(TextureManager.getPlayerTextures());
    }
}
